package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.AcctItemTypeDao;
import com.wuye.entity.AcctItemType;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository(value = "acctItemTypeDao")
public class AcctItemTypeDaoImpl extends BaseDaoHibernate implements AcctItemTypeDao {
    public PageInfo getAcctItemType(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        hql.append("from AcctItemType c where 1=1 ");
        ArrayList params = new ArrayList();
        if (map.containsKey("qryType")) {
            //默认取大类，即parentAcctTypeId为空的
            if (!StrUtil.isNullOrEmpty(map.get("qryType"))
                    && "top".equals(map.get("qryType"))) {
                hql.append(" and c.parentAcctTypeId='0'");
            } else {
                if (map.containsKey("parentAcctItemTypeId")
                        && !StrUtil.isNullOrEmpty(map.get("parentAcctItemTypeId"))) {
                    hql.append(" and c.parentAcctTypeId= ? ");
                    params.add(map.get("parentAcctItemTypeId"));
                }
            }
        }
        PageInfo pageInfo = super.findPageInfoByHQLAndParams(hql.toString(), params, 0, 3000);
        List list = new ArrayList();
        if (!map.containsKey("convert")) {
            if (pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0) {
                for (int i = 0; i < pageInfo.getDataList().size(); i++) {
                    JSONObject jsObj = new JSONObject();
                    JSONObject destAcctItemType = new JSONObject();
                    AcctItemType acctItemType = (AcctItemType) pageInfo.getDataList().get(i);
                    EntityCopyUtil.populate(destAcctItemType, acctItemType,
                            new String[]{"acctTypeName", "acctItemTypeId", "acctType", "parentAcctTypeId"
                                    , "tempAcctItemTypeId", "unit"});
                    list.add(destAcctItemType);
                }
            }
            pageInfo.setDataList(list);
        }
        return pageInfo;
    }
}
