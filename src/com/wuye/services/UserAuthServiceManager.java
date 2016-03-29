package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.UserAuth;

public interface UserAuthServiceManager extends BaseManager {
    void saveUserAuth(UserAuth userAuth);
}
