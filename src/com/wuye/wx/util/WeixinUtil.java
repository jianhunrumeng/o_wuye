package com.wuye.wx.util;

import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.entity.AttrSpec;
import com.wuye.services.CommonServiceManager;
import com.wuye.util.SysProvider;
import com.wuye.wx.pojo.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * 公众平台通用接口工具类
 *
 * @author liuyq
 * @date 2013-08-09
 */
public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    private static CommonServiceManager commonServiceManager = SpringUtil.getBean("commonServiceManager");

    /**
     * 发起 https 请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过 JSONObject.get(key)的方式获取 json 对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl,
                                         String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建 SSLContext 对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述 SSLContext 对象中得到 SSLSocketFactory 对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                    .openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    // 获取 access_token 的接口地址（GET） 限 200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取 access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAccessToken() {
        String appid = "";
        String appsecret = "";
        String retToken = "";
        AttrSpec accToken = SysProvider.getAttrSpec("sys_assist", "access_token");
        Date StatusDate = accToken.getStatusDate();
        if (!StrUtil.isNullOrEmpty(accToken.getDefalueValue())
                && StatusDate != null
                && StatusDate.compareTo(new Date()) > 0) {
            return accToken.getDefalueValue();
        }
        AttrSpec appidAttr = SysProvider.getAttrSpec("sys_assist", "appID");
        AttrSpec appsecretAttr = SysProvider.getAttrSpec("sys_assist", "appsecret");
        /*if (true){
			//Date expDate = DateUtil.dateAdd("s", 7200, new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 7200);
			accToken.setStatusDate(calendar.getTime());
			commonServiceManager.save(accToken);
			return accToken.getDefalueValue();
		}*/
        if (appidAttr != null) {
            appid = appidAttr.getDefalueValue();
        }
        if (appsecretAttr != null) {
            appsecret = appsecretAttr.getDefalueValue();
        }
        //超过7200s的要重新获取凭证
        String requestUrl = access_token_url.replace("APPID", appid).replace(
                "APPSECRET", appsecret);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                if (StatusDate == null) {
                    StatusDate = new Date();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, 7200);
                accToken.setStatusDate(calendar.getTime());

                retToken = jsonObject.getString("access_token");
                accToken.setDefalueValue(retToken);
                commonServiceManager.save(accToken);
            } catch (JSONException e) {
                retToken = "";
                // 获取 token 失败
                log.error("获取 token 失败 errcode:{} errmsg:{}",
                        jsonObject.getInt("errcode"),
                        jsonObject.getString("errmsg"));
            }
        }
        return retToken;
    }

    // 菜单创建（POST） 限 100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 有效的 access_token
     * @return 0 表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;

        // 拼装创建菜单的 url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成 json 字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 调用接口创建菜单
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}",
                        jsonObject.getInt("errcode"),
                        jsonObject.getString("errmsg"));
            }
        }

        return result;
    }
}