package com.wuye.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wuye.common.util.string.StrUtil;
import com.wuye.entity.Community;
import com.wuye.entity.Role;
import com.wuye.entity.User;
import com.wuye.services.LoginServiceManager;
import com.wuye.services.RoleServiceManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport {
    @Autowired
    private LoginServiceManager loginServiceManager;
    @Autowired
    private RoleServiceManager roleServiceManager;
    private String account;
    private String tip = "";
    private String password;
    private String autoSecurityCode;
    private boolean yjdl;
    private boolean logout_suc;

    public String checkUser() {
        User user = (User) ActionContext.getContext().getSession().get("user");
        if (user == null) {
            yjdl = false;
        } else {
            yjdl = true;
        }
        return "yjdl";
    }

    public String execute() throws Exception {
        User user = null;
        ActionContext ctx = ActionContext.getContext();
        Map<String, Object> map = new HashMap<String, Object>();
        Object securityCode = ctx.getSession().get("securityCode");
        if (!StrUtil.isNullOrEmpty(autoSecurityCode) && !StrUtil.isNullOrEmpty(securityCode) && autoSecurityCode.equals(securityCode.toString())) {
            user = loginServiceManager.login(account, password);

            if (!StrUtil.isNullOrEmpty(user)) {
                ctx.getSession().clear();
                ctx.getSession().put("user", user);
                if (user.getOwnerCompany() != null) {
                    ctx.getSession().put("companyId", user.getOwnerCompany().getCompanyId());
                }
                Community ownerCommunity = user.getOwnerCommunity();
                if (ownerCommunity != null) {
                    ctx.getSession().put("communityId", ownerCommunity.getId());
                }
                List<Role> roleList = roleServiceManager.getRoleListByUserId(user.getId());
                ctx.getSession().put("roleList", roleList);
                map.put("result", true);
                map.put("msg", "登录成功");
            } else {
                map.put("result", false);
                map.put("msg", "登录失败");
            }
        } else {
            map.put("result", null);
            map.put("msg", "验证码错误");
        }

        JSONObject json = JSONObject.fromObject(map);
        tip = json.toString();
        return SUCCESS;
    }

    /**
     * 退出功能
     *
     * @return
     */
    public String logoutFun() {
        ActionContext ctx = ActionContext.getContext();
        ctx.getSession().clear();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", true);
        JSONObject json = JSONObject.fromObject(map);
        tip = json.toString();
        return SUCCESS;
    }

    public LoginServiceManager getLoginServiceManager() {
        return loginServiceManager;
    }


    public void setLoginServiceManager(LoginServiceManager loginServiceManager) {
        this.loginServiceManager = loginServiceManager;
    }


    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }


    public String getTip() {
        return tip;
    }


    public void setTip(String tip) {
        this.tip = tip;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isYjdl() {
        return yjdl;
    }

    public void setYjdl(boolean yjdl) {
        this.yjdl = yjdl;
    }

    public boolean isLogout_suc() {
        return logout_suc;
    }

    public void setLogout_suc(boolean logoutSuc) {
        logout_suc = logoutSuc;
    }

    public String getAutoSecurityCode() {
        return this.autoSecurityCode;
    }

    public void setAutoSecurityCode(String autoSecurityCode) {
        this.autoSecurityCode = autoSecurityCode;
    }
}
