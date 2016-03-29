package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.entity.UserAuth;

public interface UserAuthDao extends Dao {
    /**
     * 保存UserAuth信息
     *
     * @param userAuth
     */
    void saveUserAuth(UserAuth userAuth);

    /**
     * 删除UserAuth
     *
     * @param roleId
     */
    void removeUserAuthByRoleId(int roleId);
}
