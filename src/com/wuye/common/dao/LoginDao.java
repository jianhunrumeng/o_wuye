package com.wuye.common.dao;

import com.wuye.entity.Privilege;
import com.wuye.entity.User;

import java.util.List;


public interface LoginDao {
    boolean login(String username, String password) throws Exception;

    List<Privilege> queryMenuPrivileges(User user, String privilege_type);

    Privilege getPrivilege(int privilege_id);

    List<Privilege> queryChildPrivileges(int privilege_id);
}
