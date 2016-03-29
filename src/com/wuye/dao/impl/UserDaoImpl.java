package com.wuye.dao.impl;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.UserDao;
import com.wuye.entity.Organization;
import com.wuye.entity.PropertyCompany;
import com.wuye.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoHibernate implements UserDao {
    public User getUserByAccountAndPwd(String account, String pwd) {

        String sql = "select a.* from user a  where a.account=? and a.pwd=? ";
        List list = new ArrayList();
        list.add(account);
        list.add(pwd);
        PageInfo pageInfo = super.findPageInfoByJDBCSQLAndParams(sql, list, 1, 1, false, User.class);
        if (pageInfo != null && pageInfo.getDataList() != null
                && pageInfo.getDataList().size() > 0) {
            User user = (User) pageInfo.getDataList().get(0);
            return user;
        } else {
            return null;
        }
    }

    public User getUserByAccount(String account) {
        String sql = "select * from user where account = ? ";
        List params = new ArrayList();
        params.add(account);
        List<User> userList = this.jdbcFindList(sql, User.class, params);
        if (!StrUtil.isNullOrEmpty(userList) && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public PageInfo getUser(Map<String, Object> map, int currentPage,
                            int perPageNum) {
        StringBuffer hql = new StringBuffer();
        List params = new ArrayList();
        if (map == null) {
            return null;
        }
        String qryType = StrUtil.strnull(map.get("qryType"));
        if (qryType.equals("company")) {
            //根据物业公司查询
            if (!StrUtil.isNullOrEmpty(map.get("companyName"))) {
                String companyName = StrUtil.strnull(map.get("companyName"));
                hql.setLength(0);
                params.clear();
                hql.append("select a from User a, PropertyCompany b where a.ownerCompany = b and b.companyName like ? ");
                params.add("%" + companyName + "%");
                return super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
            }
            return null;
        } else if (qryType.equals("name")) {
            //根据名字查询
            String partyName = StrUtil.strnull(map.get("partyName"));
            if (!partyName.equals("")) {
                hql.setLength(0);
                params.clear();
                hql.append(" select u from User u, PartyInfo p ")
                        .append("where u.userId=p.objId ")
                        .append(" and p.classId = ? ")
                        .append(" and p.partyName like ? ");
                params.add(BaseConstants.CLASS_USER);
                params.add("%" + partyName + "%");
                return super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
            }
        } else if (qryType.equals("user")) {
            String account = StrUtil.strnull(map.get("account"));
            if (!account.equals("")) {
                hql.setLength(0);
                params.clear();
                hql.append(" from User u ")
                        .append("where 1=1 ")
                        .append(" and u.account = ? ");
                params.add(account);
                return super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
            }
        } else if (qryType.equals("all")) {
            hql.setLength(0);
            params.clear();
            hql.append(" from User u ");
            return super.findPageInfoByHQLAndParams(hql.toString(), params, currentPage, perPageNum);
        }
        return null;
    }

    public PageInfo getUser(PropertyCompany company, int currentPage, int perPageNum) {
        List<Organization> orgs = new ArrayList<Organization>();
        List<Organization> childOrgs = company.getOrganization().getAllChild();
        if (childOrgs != null && childOrgs.size() > 0) {
            orgs.addAll(childOrgs);
        }
        orgs.add(company.getOrganization());
        StringBuffer hql = new StringBuffer();
        hql.append(" select a from User a, UserOrgRel b")
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

    public List<User> getUserByOpendId(String opendId) {
        String sql = "select * from user where opend_id = ? ";
        List params = new ArrayList();
        params.add(opendId);
        List<User> userList = this.jdbcFindList(sql, User.class, params);
        return userList;
    }
}
