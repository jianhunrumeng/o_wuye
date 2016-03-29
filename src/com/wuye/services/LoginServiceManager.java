package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.User;

import java.util.List;
import java.util.Map;

public interface LoginServiceManager extends BaseManager {
    User login(String username, String password) throws Exception;

    List queryMenuPrivileges(Map<String, Object> map);
}
