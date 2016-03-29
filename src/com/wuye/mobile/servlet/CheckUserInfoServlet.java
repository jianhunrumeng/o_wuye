package com.wuye.mobile.servlet;

import com.wuye.common.util.SpringUtil;
import com.wuye.entity.User;
import com.wuye.services.LoginServiceManager;
import com.wuye.services.UserServiceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class CheckUserInfoServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(CheckUserInfoServlet.class);
    private UserServiceManager userServiceManager = SpringUtil.getBean("userServiceManager");
    private LoginServiceManager loginServiceManager = SpringUtil.getBean("loginServiceManager");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        log.info("进入get方法");
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
        String mobilePhone = request.getParameter("mobilePhone");
        String password = request.getParameter("password");
        log.info("请求类型：" + type);
        String isExit = "no";
        if ("checkPhone".equals(type)) { //判断手机号是否存在
            log.info("手机号码：" + mobilePhone);
            User user = userServiceManager.getUserByAccount(mobilePhone.trim());
            if (user != null && (user.getOpendId() == null || "".equals(user.getOpendId()))) {
                isExit = "yes";
            } else if (user != null && (user.getOpendId() != null || !"".equals(user.getOpendId()))) {
                isExit = "isHas";
            }
        } else if ("checkPassword".equals(type)) {
            log.info("用户密码：" + password);
            try {
                User user = loginServiceManager.login(mobilePhone.trim(), password.trim());
                if (user != null) {
                    isExit = "yes";
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        PrintWriter out = response.getWriter();
        out.print(isExit);
    }
}
