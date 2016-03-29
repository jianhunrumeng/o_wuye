package com.wuye.mobile.servlet;

import com.wuye.mobile.entity.HandleMsgAdapter;
import com.wuye.mobile.entity.WuYeSession;
import org.marker.utils.MySecurity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 处理微信服务器请求的Servlet URL地址：http://xxx/weixin/dealwith.do
 * <p/>
 * 注意：官方文档限制使用80端口哦！
 *
 * @author marker
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class WeiXinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // TOKEN 是你在微信平台开发模式中设置的哦
    public static final String TOKEN = "wuyeWeixin";

    /**
     * 处理微信服务器验证
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");// 随机字符串

        // 重写totring方法，得到三个参数的拼接字符串
        List<String> list = new ArrayList<String>(3) {
            private static final long serialVersionUID = 2621444383666420433L;

            public String toString() {
                return this.get(0) + this.get(1) + this.get(2);
            }
        };
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);// 排序
        String tmpStr = new MySecurity().encode(list.toString(),
                MySecurity.SHA_1);// SHA-1加密
        Writer out = response.getWriter();
        if (signature.equals(tmpStr)) {
            out.write(echostr);// 请求验证成功，返回随机码
        } else {
            out.write("");
        }
        out.flush();
        out.close();
    }


    /**
     * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String url = request.getRequestURL().toString();
        int num = url.indexOf("/wxserver");
        url = url.substring(0, num);
        InputStream is = request.getInputStream();
        OutputStream os = response.getOutputStream();
        final WuYeSession session = WuYeSession.newInstance();

        session.addOnHandleMessageListener(new HandleMsgAdapter(session));

        session.process(is, os, null, url);
        session.close();

    }

}
