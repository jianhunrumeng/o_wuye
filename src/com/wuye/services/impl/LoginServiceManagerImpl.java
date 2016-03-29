package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.dao.UserDao;
import com.wuye.entity.User;
import com.wuye.services.LoginServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("loginServiceManager")
public class LoginServiceManagerImpl extends BaseManagerImpl implements LoginServiceManager {

    @Autowired
    private UserDao userDao;


    public User login(String username, String password) throws Exception {
        User user = userDao.getUserByAccountAndPwd(username, password);
        if (user != null) {
            return user;
        }
        return null;
    }

    public List queryMenuPrivileges(Map<String, Object> map) {
        // TODO Auto-generated method stub
//		return loginDao.queryMenuPrivileges(map);
        return null;
    }

}
