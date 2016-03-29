package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.AcctItemTypeDao;
import com.wuye.services.AcctItemTypeServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("acctItemTypeServiceManager")
public class AcctItemTypeServiceManagerImpl extends BaseManagerImpl implements
        AcctItemTypeServiceManager {
    @Autowired
    AcctItemTypeDao acctItemtypeDao;

    public RetVO getAcctItemType(Map<String, Object> map) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = acctItemtypeDao.getAcctItemType(map);
//		List<Object> dataList = pg.getDataList();
        retVO.setPageInfo(pg);
        return retVO;
    }
}
