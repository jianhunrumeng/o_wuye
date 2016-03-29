package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.common.vo.PageInfo;
import com.wuye.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao extends Dao {
    User getUserByAccountAndPwd(String Account, String pwd);

    /**
     * 根据帐号查询User
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 获取用户信息
     *
     * @param map
     * @param currentPage
     * @param perPageNum
     * @return
     */
    PageInfo getUser(Map<String, Object> map,
                     final int currentPage, final int perPageNum);

    /**
     * 根据微信opendId查询用户
     *
     * @param opendId
     * @return
     */
    List<User> getUserByOpendId(String opendId);
}
