package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.numeric.NumericUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.CommunityDao;
import com.wuye.dao.PropertyCompanyDao;
import com.wuye.dao.UserDao;
import com.wuye.entity.*;
import com.wuye.services.UserServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("userServiceManager")
public class UserServiceManagerImpl extends BaseManagerImpl implements UserServiceManager {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CommunityDao communityDao;
    @Autowired
    private PropertyCompanyDao propertyCompanyDao;

    public void saveUser(Map<String, Object> map) {
        // TODO Auto-generated method stub
        User user = (User) map.get("user");
        if (map.containsKey("proertyCompany")) {
            PropertyCompany propertyCompany = (PropertyCompany) map.get("proertyCompany");
            user.setOwnerCompany(propertyCompany);
        }
        user.save();
        Address address;
        if (map.containsKey("area") && map.containsKey("address")) {
            Area area = (Area) map.get("area");
            address = (Address) map.get("address");
            address.setArea(area);
            address.save();
        } else {
            address = new Address();
        }
        if (map.containsKey("partyInfo")) {
            PartyInfo partyInfo = (PartyInfo) map.get("partyInfo");
            partyInfo.setClassId(BaseConstants.CLASS_USER);            //表示用户
            partyInfo.setObjId(user.getId());
            partyInfo.setStatusCd(user.getStatusCd());
            partyInfo.setMobile(user.getAccount());
            partyInfo.setAddress(address);
            partyInfo.save();
        }
        if (map.containsKey("roleList")) {
            List<Role> roleList = (ArrayList<Role>) map.get("roleList");
            for (Role role : roleList) {
                UserAuth userAuth = new UserAuth();
                userAuth.setRole(role);
                userAuth.setUser(user);
                userAuth.setStatusCd("1000");
                userAuth.save();
            }
        }
        if (map.containsKey("cmmunity")) {
            Community community = (Community) map.get("cmmunity");
            community = (Community) communityDao.getObject(Community.class, community.getId());
            if (!StrUtil.isNullOrEmpty(community) && !StrUtil.isNullOrEmpty(community.getOrganization())
                    && !StrUtil.isNullOrEmpty(community.getOrganization().getId())) {
                UserOrgRel userOrgRel = new UserOrgRel();
                userOrgRel.setUser(user);
                userOrgRel.setOrganization(community.getOrganization());
                userOrgRel.save();
            }
        } else if (map.containsKey("proertyCompany")) {
            PropertyCompany propertyCompany = (PropertyCompany) map.get("proertyCompany");
            propertyCompany = (PropertyCompany) propertyCompanyDao.getObject(PropertyCompany.class, propertyCompany.getId());
            if (!StrUtil.isNullOrEmpty(propertyCompany) && !StrUtil.isNullOrEmpty(propertyCompany.getOrganization())
                    && !StrUtil.isNullOrEmpty(propertyCompany.getOrganization().getId())) {
                UserOrgRel userOrgRel = new UserOrgRel();
                userOrgRel.setUser(user);
                userOrgRel.setOrganization(propertyCompany.getOrganization());
                userOrgRel.save();
            }
        }
    }

    public boolean isExitAccount(String account) {
        User user = userDao.getUserByAccount(account);
        if (user != null) {
            return true;
        }
        return false;
    }

    public RetVO getUser(Map<String, Object> map,
                         final int currentPage, final int perPageNum) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        PageInfo pg = userDao.getUser(map, currentPage, perPageNum);
        List<Object> dataList = pg.getDataList();
        JSONArray jsArray = new JSONArray();
        if (dataList != null && dataList.size() > 0) {
            for (Object obj : dataList) {
                User user = (User) obj;
                PartyInfo partyInfo = user.getPartyInfo();
                JSONObject jsobj = EntityCopyUtil.getJSONObject(user, null);
                jsobj.put("partyInfo", EntityCopyUtil.getJSONObject(partyInfo, null));
                PropertyCompany company = user.getOwnerCompany();
                jsobj.put("company", EntityCopyUtil.getJSONObject(company, null));
                jsArray.add(jsobj);
            }
        }
        retVO.setObject(jsArray);
        return retVO;
    }

    public User getUserBindingsWeiXin(String opendId) {
        List<User> userList = userDao.getUserByOpendId(opendId);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public boolean updateUserOpendId(User user) {
        User oldUser = userDao.getUserByAccount(user.getAccount());
        if (oldUser != null) {
            oldUser.setOpendId(user.getOpendId());
            oldUser.setOpendTime(user.getOpendTime());
            oldUser.save();
            return true;
        }
        return false;
    }

    public User getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }

    /**
     * {@inheritDoc}
     *
     * @author Luxb
     * 2015-12-19 Luxb
     * @see com.wuye.services.UserServiceManager#qryUserList(java.util.Map, int, int)
     */
    public PageInfo qryUserList(Map<String, Object> map, int currentPage, int perPageNum) {
        return userDao.getUser(map, currentPage, perPageNum);
    }

    public RetVO del(Map<String, Object> map) throws Exception {
        RetVO retVo = RetVO.newInstance(BaseConstants.RET_TRUE, "权限删除成功!");

        JSONArray users = (JSONArray) map.get("userIdArray");
        if (users != null && users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                String[] str = users.getString(i).split(";");
                int userId = NumericUtil.toInt(str[0]);
                int partyId = NumericUtil.toInt(str[1]);
                int addressId = NumericUtil.toInt(str[2]);
                //删除用户
                userDao.removeObject(User.class, userId);
                //用户信息删除
                userDao.removeObject(PartyInfo.class, partyId);
                //删除地址
                userDao.removeObject(Address.class, addressId);
                //删除用户与角色关联信息
                StringBuffer sql = new StringBuffer();
                List<Object> param = new ArrayList<Object>();
                sql.append("delete from user_auth where user_id=?");
                param.add(userId);
                this.dao.executeSql(sql.toString(), param);
            }
        }

        return retVo;
    }
}
