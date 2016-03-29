package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.entity.User;

import java.util.Map;

public interface UserServiceManager extends BaseManager {
    void saveUser(Map<String, Object> map);

    /**
     * 账号是否已经存在
     *
     * @param account
     * @return
     */
    boolean isExitAccount(String account);

    RetVO getUser(Map<String, Object> map,
                  final int currentPage, final int perPageNum);

    /**
     * 查看用户帐号是否和微信绑定
     *
     * @param opendId
     * @return
     */
    User getUserBindingsWeiXin(String opendId);

    /**
     * 用户和微信号进行绑定
     *
     * @param user
     * @return
     */
    boolean updateUserOpendId(User user);

    /**
     * 根据手机帐号获取用户信息
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 客户信息获取.
     *
     * @param map
     * @param currentPage
     * @param perPageNum
     * @return
     * @author Luxb
     * 2015-12-19 Luxb
     */
    PageInfo qryUserList(Map<String, Object> map, final int currentPage, final int perPageNum);

    RetVO del(Map<String, Object> map) throws Exception;
}
