package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.entity.AttrSpec;
import com.wuye.services.CommonServiceManager;
import org.springframework.stereotype.Service;

@Service("commonServiceManager")
public class CommonServiceManagerImpl extends BaseManagerImpl implements CommonServiceManager {

    public void saveAttrSpec(AttrSpec attr) {
        // TODO Auto-generated method stub
        attr.save();
    }
}
