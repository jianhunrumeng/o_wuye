package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.dao.PropertyCompanyDao;
import com.wuye.entity.Organization;
import com.wuye.entity.PropertyCompany;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("propertyCompanyDao")
public class PropertyCompanyDaoImpl extends BaseDaoHibernate implements PropertyCompanyDao {

    public void save() {
//		super.saveObject(o)
    }

    public List<PropertyCompany> queryPropertyCompanyByName(String name) {
        StringBuffer sb = new StringBuffer();
        List params = new ArrayList();
        sb.append(" select * from property_company where 1=1 ");
        if (!StrUtil.isNullOrEmpty(name)) {
            sb.append(" and company_name like ? ");
            params.add("%" + name + "%");
        }
        sb.append(" limit 5 ");
        List<PropertyCompany> pcList = this.jdbcFindList(sb.toString(), PropertyCompany.class, params);
        return pcList;
    }

    public PageInfo getUser(PropertyCompany company, int currentPage, int perPageNum) {
        List<Organization> orgs = new ArrayList<Organization>();
        List<Organization> childOrgs = company.getOrganization().getAllChild();
        if (childOrgs != null && childOrgs.size() > 0) {
            orgs.addAll(childOrgs);
        }
        orgs.add(company.getOrganization());
        StringBuffer hql = new StringBuffer();
        hql.append("select a from User a, UserOrgRel b")
                .append(" where 1=1")
                .append(" where a.userId= b.userId")
                .append(" and b.orgId in (?)");
        List<Integer> orgIds = new ArrayList<Integer>();
        for (Organization org : orgs) {
            orgIds.add(org.getId());
        }
        List<Object> params = new ArrayList<Object>();
        params.add(orgIds.toArray(new Integer[0]));
        return super.findPageInfoByJDBCSQLAndParams(hql.toString(), params, currentPage, perPageNum);
    }
}
