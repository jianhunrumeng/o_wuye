package com.wuye.services;

import com.wuye.common.services.BaseManager;
import com.wuye.entity.PropertyCompany;

public interface CompanyServiceManager extends BaseManager {
    public void save(PropertyCompany propertyCompany);
}
