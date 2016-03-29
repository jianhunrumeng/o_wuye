/**
 *
 */
package com.wuye.common.interceptor;

import com.wuye.entity.User;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 自定义过滤器，过滤.jsp请求，防止无登录直接访问
 *
 * @author kirby24
 */
public class OnlineFilter implements Filter, StrutsStatics {

    private static Logger log = Logger.getLogger(OnlineFilter.class);
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

		/*
		 * RequestDispatcher dispatcher = request
		 * .getRequestDispatcher("../login.jsp");// 这里设置如果没有登陆将要转发到的页面
		 */
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        String uri = req.getRequestURI();
        String url = req.getRequestURL().toString();
        String project = "";
        if (!uri.equals("/")) {
            if (uri.indexOf("wuye") > 0) {
                project = uri.substring(0, uri.indexOf("/", 1) + 1);
            } else {
                project = uri.substring(0, uri.indexOf("/", 0) + 1);
            }
        }


        // 从session里取的用户名信息
        //String user_name = (String) session.getAttribute("user_name");
        // 这里获取session，为了检查session里有没有保存用户信息，没有的话回转发到登陆页面
        User user = (User) session.getAttribute("user");
        String path = req.getServletPath();

        boolean pb = true;
        if (path != null) {
            if (path.indexOf("login.html") > 0 || path.indexOf("userBindings.jsp") > 0) {
                pb = false;
            }
        }
        // 判断如果没有取到用户信息,就跳转到登陆页面
        if ((user == null) && pb) {

            log.error("用户没有登陆，不允许操作");

            // 跳转到登陆页面
//			dispatcher.forward(request, response);
            res.setHeader("Cache-Control", "no-store");
            res.setDateHeader("Expires", 0);
            res.setHeader("Pragma", "no-cache");
            res.sendRedirect(project + "login.html");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp"); 
//			dispatcher.forward(request, response);
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
            // System.out.println("用户已经登陆，允许操作");
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {

    }

}
