package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.entity.Privilege;
import com.wuye.entity.User;
import com.wuye.services.LoginService;

import java.util.List;

public class LoginServiceImpl extends BaseManagerImpl implements LoginService {
    /*
    private LoginDaoImpl loginDao;

    public void setLoginDao(LoginDaoImpl loginDao) {
        this.loginDao = loginDao;
    }*/
    public boolean login(String username, String password) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public List queryMenuPrivileges(User user, String privilege_type) {
        // TODO Auto-generated method stub
        return null;
    }

    public Privilege getPrivilege(int privilege_id) {
        return null;
    }

    public List<Privilege> queryChildPrivileges(int privilege_id) {
        return null;
    }

}
