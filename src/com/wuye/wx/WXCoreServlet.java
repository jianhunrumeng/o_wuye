package com.wuye.wx;

import com.wuye.common.util.string.StrUtil;
import com.wuye.entity.AttrSpec;
import com.wuye.util.SysProvider;
import com.wuye.wx.pojo.Button;
import com.wuye.wx.pojo.ComplexButton;
import com.wuye.wx.pojo.Menu;
import com.wuye.wx.pojo.ViewButton;
import com.wuye.wx.util.SignUtil;
import com.wuye.wx.util.WeixinUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WXCoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = StrUtil.strnull(request.getParameter("action"));
        PrintWriter out = response.getWriter();
        if ("getToken".equals(action)) {
            String accToken = WeixinUtil.getAccessToken();
            out.print(accToken);
        } else if ("setMenu".equals(action)) {
            String accToken = WeixinUtil.getAccessToken();
            //删除菜单
            JSONObject retJobj = WeixinUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accToken, "GET", null);

            int result = WeixinUtil.createMenu(genMenu(request), accToken);

            return;
        } else {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");

            if (StrUtil.isNullOrEmpty(signature)
                    || StrUtil.isNullOrEmpty(timestamp)
                    || StrUtil.isNullOrEmpty(nonce)
                    || StrUtil.isNullOrEmpty(echostr)) {
                out.close();
                out = null;
                return;
            }
            // 通过检验 signature 对请求进行校验， 若校验成功则原样返回 echostr， 表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        }

        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO 消息的接收、处理、响应
        // 将请求、响应的编码均设置为 UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = WXCoreService.processRequest(request);

        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    private void setMenue() {

    }

    private Menu genMenu(HttpServletRequest request) {
        AttrSpec attr = SysProvider.getAttrSpec("sys_assist", "context_addr");
        String contextAddr = attr.getDefalueValue();
        if (StrUtil.isNullOrEmpty(contextAddr)) {
            contextAddr = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort() + "/";
        }
        if (!contextAddr.endsWith("/")) {
            contextAddr = contextAddr + "/";
        }


        ComplexButton yz = new ComplexButton();
        yz.setName("业主");

        ViewButton yz1 = new ViewButton();
        yz1.setName("绑定");
        yz1.setType(WxConstants.MENU_TYPE_VIEW);
        yz1.setUrl(contextAddr + "jsp/mobile/userBindings.jsp");

        yz.setSub_button(new Button[]{yz1});

        ComplexButton wy = new ComplexButton();
        wy.setName("物业");

        ViewButton wy1 = new ViewButton();
        wy1.setName("绑定");
        wy1.setType(WxConstants.MENU_TYPE_VIEW);
        wy1.setUrl(contextAddr + "jsp/mobile/userBindings.jsp");

        wy.setSub_button(new Button[]{wy1});

        ComplexButton more = new ComplexButton();
        more.setName("更多");

        ViewButton more1 = new ViewButton();
        more1.setName("绑定");
        more1.setType(WxConstants.MENU_TYPE_VIEW);
        more1.setUrl(contextAddr + "jsp/mobile/userBindings.jsp");

        more.setSub_button(new Button[]{more1});

        Menu menu = new Menu();
        menu.setButton(new Button[]{yz, wy, more});

        return menu;
    }
}
