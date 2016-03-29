package com.wuye.mobile.servlet;

import com.wuye.common.util.SpringUtil;
import com.wuye.common.util.date.DateUtil;
import com.wuye.entity.User;
import com.wuye.services.UserServiceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class UserOpendBindingsServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(UserOpendBindingsServlet.class);
    private UserServiceManager userServiceManager = SpringUtil.getBean("userServiceManager");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOpendBindingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入GET");
        doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String type = request.getParameter("type");
        log.info("绑定类型：" + type);
        String isSuccess = "no";
        if ("simpleBindings".endsWith(type)) {
            Object wxObj = request.getSession().getAttribute("wxid");
            String mobilePhone = request.getParameter("mobilePhone");
            if (wxObj != null) {
                String opendId = wxObj.toString();
                log.info("微信opendId：" + opendId + ",手机号：" + mobilePhone);
                String opendTime = DateUtil.getDateTimeStr(DateUtil.getCurTime());
                User user = new User();
                user.setAccount(mobilePhone);
                user.setOpendId(opendId);
                user.setOpendTime(opendTime);
                boolean bl = userServiceManager.updateUserOpendId(user);
                if (bl) {
                    isSuccess = "yes";
                }
            }
        }
        PrintWriter out = response.getWriter();
        out.print(isSuccess);
    }
}
