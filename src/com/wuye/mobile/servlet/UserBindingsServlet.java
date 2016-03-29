package com.wuye.mobile.servlet;

import com.wuye.common.util.SpringUtil;
import com.wuye.entity.User;
import com.wuye.services.UserServiceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserBindingsServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(UserBindingsServlet.class);
    private UserServiceManager userServiceManager = SpringUtil.getBean("userServiceManager");

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBindingsServlet() {
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
        String wxid = request.getParameter("wxid");
        if ("userBindings".equals(type)) {
            if (wxid == null || wxid == "") {
                request.getRequestDispatcher("/jsp/mobile/warning.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("wxid", wxid);
                User user = userServiceManager.getUserBindingsWeiXin(wxid);
                if (user != null) {
                    if ("11".equals(user.getUserType())) {   //表示物业用户
                        request.getRequestDispatcher("/jsp/mobile/propertyMenu.jsp?account=" + user.getAccount() + "").forward(request, response);
                    } else if ("12".equals(user.getUserType())) {  //表示业主用户
                        request.getRequestDispatcher("/jsp/mobile/businessMenu.jsp?account=" + user.getAccount() + "").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/jsp/mobile/warning.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/jsp/mobile/userBindings.jsp").forward(request, response);
                }
            }
        }
    }
}
