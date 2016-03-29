package com.wuye.dao;

import com.wuye.common.dao.Dao;
import com.wuye.entity.PropertyCompany;

import java.util.List;

public interface PropertyCompanyDao extends Dao {
    void save();

    /**
     * 查询物业公司By
     *
     * @param name
     * @return
     */
    List<PropertyCompany> queryPropertyCompanyByName(String name);
}
