package com.wuye.services.impl;

import com.wuye.common.services.impl.BaseManagerImpl;
import com.wuye.common.util.bean.EntityCopyUtil;
import com.wuye.common.util.string.StrUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.common.vo.RetVO;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.PartyInfoDao;
import com.wuye.dao.PropertyCompanyDao;
import com.wuye.entity.*;
import com.wuye.services.PropertyCompanyServiceManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("propertyCompanyServiceManager")
public class PropertyCompanyServiceManagerImpl extends BaseManagerImpl implements PropertyCompanyServiceManager {
    @Autowired
    PropertyCompanyDao propertyCompanyDao;
    @Autowired
    PartyInfoDao partyInfoDao;

    public void savePreopertyCompany(Map<String, Object> map) throws Exception {
        JSONArray propertyCompanys = (JSONArray) map.get("propertyCompany");
        if (propertyCompanys != null && propertyCompanys.size() > 0) {
            for (int i = 0; i < propertyCompanys.size(); i++) {
                JSONObject srcCompany = propertyCompanys.getJSONObject(i);
                if (StrUtil.isNullOrEmpty(srcCompany.get("companyId"))) {
                    // 新增
                    this.add(srcCompany);
                } else {
                    // 更改
                    this.update(srcCompany);
                }
            }
        }
        /*if(map.containsKey("companyId")){
			PropertyCompany propertyCompany=(PropertyCompany)map.get("propertyCompany");
			PropertyCompany propertyCompanySrc=null;
			map.put("convert", "false");
			PageInfo pageInfo=this.getPreopertyCompanyList(map, 1, 1);
			if(pageInfo!=null&&pageInfo.getDataList().size()>0){
				propertyCompanySrc=(PropertyCompany)pageInfo.getDataList().get(0);
			}
			if(propertyCompanySrc!=null){
				Organization organizationsrc=propertyCompanySrc.getOrganization();//原始组织
				if(organizationsrc==null){
					organizationsrc=new Organization();
				}
				PartyInfo companyInfo=propertyCompanySrc.getCompanyInfo();//原始公司信息
				Address addressSrc=null;
				if(companyInfo!=null){
					addressSrc=companyInfo.getAddress();//原始公司地址信息
				}else{
					companyInfo=new PartyInfo();
				}
				if(addressSrc==null){
					addressSrc=new Address();
				}
				PartyInfo partyInfosrc=propertyCompanySrc.getPartyInfo();//原始联系人
				if(partyInfosrc==null){
					partyInfosrc=new PartyInfo();
				}
				BeanUtilsExtend.copyPropertiesIgnoreNull(propertyCompanySrc,propertyCompany);
				BeanUtilsExtend.copyPropertiesIgnoreNull(organizationsrc,propertyCompany.getOrganization());
				BeanUtilsExtend.copyPropertiesIgnoreNull(companyInfo,propertyCompany.getCompanyInfo());
				BeanUtilsExtend.copyPropertiesIgnoreNull(addressSrc,propertyCompany.getCompanyInfo().getAddress());
				BeanUtilsExtend.copyPropertiesIgnoreNull(partyInfosrc,propertyCompany.getPartyInfo());
				propertyCompanySrc.setOrganization(organizationsrc);
				propertyCompanySrc.setPartyInfo(partyInfosrc);
				companyInfo.setAddress(addressSrc);
				propertyCompanySrc.setCompanyInfo(companyInfo);
				propertyCompanySrc.save();
			}
		}else{
			PropertyCompany propertyCompany=(PropertyCompany)map.get("propertyCompany");
			propertyCompany.save();
		}*/

    }

    private void update(JSONObject srcCompany) {
        // TODO Auto-generated method stub
        PropertyCompany company = (PropertyCompany) PropertyCompany.getDao().getObject(PropertyCompany.class, srcCompany.getInt("companyId"));
        Organization organization = company.getOrganization();
        PartyInfo companyInfo = company.getCompanyInfo();
        PartyInfo leaderInfo = company.getPartyInfo();
        Address add = null;
        if (organization == null) {
            organization = new Organization();
        }
        if (companyInfo == null) {
            companyInfo = new PartyInfo();
            add = new Address();
        } else {
            add = companyInfo.getAddress();
            if (add == null) {
                add = new Address();
            }
        }
        if (leaderInfo == null) {
            leaderInfo = new PartyInfo();
        }

        EntityCopyUtil.populate(company, srcCompany, null);
        EntityCopyUtil.populate(companyInfo,
                srcCompany.getJSONObject("partyInfopp"), null);
        EntityCopyUtil.populate(leaderInfo,
                srcCompany.getJSONObject("partyInfo"), null);
        EntityCopyUtil.populate(add, srcCompany.getJSONObject("partyInfopp")
                .getJSONObject("address"), null);

        Area area = (Area) Area.getDefaultDao().getObject(Area.class, srcCompany.getJSONObject("partyInfopp")
                .getJSONObject("address").getInt("areaId"));
        add.setArea(area);
        companyInfo.setAddress(add);

        organization.setOrgName(company.getCompanyName());
        organization.setOrgType(BaseConstants.ORG_TYPE_COMPANY);

        company.setOrganization(organization);
        company.setCompanyInfo(companyInfo);
        company.setPartyInfo(leaderInfo);
        company.save();
    }

    private void add(JSONObject srcCompany) {
        // TODO Auto-generated method stub
        PropertyCompany company = new PropertyCompany();
        Organization organization = new Organization();
        PartyInfo companyInfo = new PartyInfo();
        PartyInfo leaderInfo = new PartyInfo();
        Address add = new Address();

        EntityCopyUtil.populate(company, srcCompany, null);
        EntityCopyUtil.populate(companyInfo,
                srcCompany.getJSONObject("partyInfopp"), null);
        EntityCopyUtil.populate(leaderInfo,
                srcCompany.getJSONObject("partyInfo"), null);
        EntityCopyUtil.populate(add, srcCompany.getJSONObject("partyInfopp")
                .getJSONObject("address"), null);

        Area area = (Area) Area.getDefaultDao().getObject(Area.class, srcCompany.getJSONObject("partyInfopp")
                .getJSONObject("address").getInt("areaId"));
        add.setArea(area);
        companyInfo.setAddress(add);

        organization.setOrgName(company.getCompanyName());
        organization.setOrgType(BaseConstants.ORG_TYPE_COMPANY);

        company.setOrganization(organization);
        company.setCompanyInfo(companyInfo);
        company.setPartyInfo(leaderInfo);
        company.save();

    }

    public PartyInfo getPartyInfo(Integer companyId) {
        // TODO Auto-generated method stub
        return partyInfoDao.getPartyInfo(BaseConstants.CLASS_COMPANY, companyId);
    }

    public PageInfo getPreopertyCompanyList(
            Map<String, Object> map, final int currentPage, final int perPageNum) {
        StringBuffer sql = new StringBuffer();
        List params = new ArrayList();
        sql.append("");
//		sql.append("select pc.company_id,pc.company_name,pc.simple_name,pi.party_name,pi.mobile,pc.status_cd ");
//		sql.append("from property_company pc,party_info pi ,party_info cpi ");
//		sql.append("where pi.party_info_id=pc.party_info_id ");
//		sql.append("and cpi.class_id='12' ");
//		sql.append("and cpi.obj_id=pc.company_id ");
        sql.append("select a  from PropertyCompany a where 1=1");
        if (map != null && !StrUtil.isNullOrEmpty(map.get("propertyCompanyName"))) {
            sql.append("and  a.companyName  like ? ");
            params.add("%" + map.get("propertyCompanyName") + "%");
        }
        if (map != null && !StrUtil.isNullOrEmpty(map.get("companyId"))) {
            sql.append("and  a.companyId=? ");
            params.add(map.get("companyId"));
        }
        PageInfo pageInfo = this.dao.findPageInfoByHQLAndParams(sql.toString(), params, currentPage, perPageNum);
        List list = new ArrayList();
        if (!map.containsKey("convert")) {
            if (pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0) {
                for (int i = 0; i < pageInfo.getDataList().size(); i++) {
                    JSONObject jsObj = new JSONObject();
                    JSONObject destPropertyCompany = new JSONObject();
                    PropertyCompany curP = (PropertyCompany) pageInfo.getDataList().get(i);
                    EntityCopyUtil.populate(destPropertyCompany, curP, null);
                    JSONObject companyInfo = new JSONObject();
                    EntityCopyUtil.populate(companyInfo, curP.getCompanyInfo(), null);
                    JSONObject partyInfo = new JSONObject();
                    EntityCopyUtil.populate(partyInfo, curP.getPartyInfo(), null);
                    destPropertyCompany.put("partyInfo", partyInfo);
                    //公司地址详情
                    JSONObject address = new JSONObject();
                    if (curP.getCompanyInfo() != null) {
                        EntityCopyUtil.populate(address, curP.getCompanyInfo().getAddress(), null);
                        if (curP.getCompanyInfo().getAddress() != null) {
                            Address addressSrc = curP.getCompanyInfo().getAddress();
                            Area area = addressSrc.getArea();
                            Area city = area.getUpArea();
                            Area province = city.getUpArea();
                            JSONObject jsarea = EntityCopyUtil.getJSONObject(area, null);
                            JSONObject jsCity = EntityCopyUtil.getJSONObject(city, null);
                            JSONObject jsProvince = EntityCopyUtil.getJSONObject(province, null);
                            jsCity.put("upArea", jsProvince);
                            jsarea.put("upArea", jsCity);
                            address.put("area", jsarea);
                        }
                    }
                    companyInfo.put("address", address);
                    destPropertyCompany.put("companyInfo", companyInfo);
                    jsObj.put("propertyCompany", destPropertyCompany);
                    list.add(jsObj);
                }
            }
            pageInfo.setDataList(list);
        }
        return pageInfo;
    }

    public void removePropertyCompany(Map<String, Object> map) throws Exception {
        if (map != null && map.containsKey("companyIdArray")) {
            if (map.containsKey("companyIdArray")) {
                JSONArray json = (JSONArray) map.get("companyIdArray");
                for (int i = 0; i < json.size(); i++) {
                    int companyId = json.getInt(i);
                    PropertyCompany comp = (PropertyCompany) this.getObject(PropertyCompany.class, companyId);
                    if (comp != null) {
                        comp.remove();
                    }
                }
            }

        }
    }

    public List<PropertyCompany> queryPropertyCompanyByName(String name) {
        // TODO Auto-generated method stub
        return propertyCompanyDao.queryPropertyCompanyByName(name);
    }

    public RetVO getCommunitySimple(Map<String, Object> paramMap) {
        RetVO retVO = RetVO.newInstance(BaseConstants.RET_TRUE, "");
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append(" from PropertyCompany c ")
                .append(" where 1=1 ")
                .append(" and c.statusCd = ? ");
        param.add(BaseConstants.STATUS_VALID);
        String companyName = StrUtil.strnull(paramMap.get("companyName"));
        if (!companyName.equals("")) {
            sql.append(" and (c.companyName like ? ")
                    .append(" 	or c.companyName like ? ) ");
            param.add("%" + companyName.toUpperCase() + "%");
            param.add("%" + companyName.toLowerCase() + "%");
        }
		
		/*sql.append(" and limit ? ");
		param.add(BaseConstants.DEFAULT_SHOW_ITEM);*/
        List<PropertyCompany> companies = propertyCompanyDao.findListByHQLAndParams(sql.toString(), param, BaseConstants.DEFAULT_SHOW_ITEM);
        JSONArray jsArray = new JSONArray();
        if (companies != null && companies.size() > 0) {
            for (PropertyCompany com : companies) {
                JSONObject jsObj = EntityCopyUtil.getJSONObject(com, new String[]{"companyId", "companyName", "regionWithSHQ"});
                jsArray.add(jsObj);
            }
        }
        retVO.setObject(jsArray);
        return retVO;
    }

}
