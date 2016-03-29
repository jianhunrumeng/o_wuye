/**
 *
 */
package com.wuye.common.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wuye.action.LoginAction;
import com.wuye.entity.User;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * session拦截器，判断session是否超时。
 *
 * @author kirby24
 */
public class SessionInterceptor extends AbstractInterceptor {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
     * .opensymphony.xwork2.ActionInvocation)
     */
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        // 获取session
        Map session = ai.getInvocationContext().getSession();
        // 获取拦截的action
        Object action = (Action) ai.getAction();
        // 如果action为登陆以及门店登录的则不拦截
        if (action instanceof LoginAction) {

            return ai.invoke();
        }

        // 得到session中的用户
        User user = (User) session.get("user");
        if (user == null) {
            //获取ajax请求头部标记
            String actionTag = ServletActionContext.getRequest().getHeader("x-requested-with");

            //if(actionTag!=null && actionTag.equalsIgnoreCase("XMLHttpRequest")){//如果是ajax请求
            if (actionTag != null && actionTag.equalsIgnoreCase("Ext.basex")) {//如果是ajax请求，为了实现ajax的同步请求方式，项目中引入了ext-basex.js拓展包，导致标记改变
                System.out.println("ajax");
                HttpServletResponse resp = ServletActionContext.getResponse();
                resp.addHeader("sessionstatus", "timeout");
                Map<String, Object> result = new HashMap<String, Object>();
                //result.put("success", false);
                result.put("timeout", true);
                result.put("redirectUrl", "login.html");
                PrintWriter out = resp.getWriter();
                out.print(JSONObject.fromObject(result));
                out.flush();
                out.close();
            }
            return Action.LOGIN;
        } else {
            return ai.invoke();
        }
//		return ai.invoke();
    }

}
