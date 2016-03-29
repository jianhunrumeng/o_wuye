package test.wuye;

import com.wuye.common.util.date.DateUtil;
import com.wuye.common.vo.PageInfo;
import com.wuye.constants.BaseConstants;
import com.wuye.dao.CommunityDao;
import com.wuye.entity.*;
import com.wuye.services.CommunityServiceManager;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import test.BaseTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class TestCommunityDao extends BaseTest {
	@Resource
	CommunityDao communityDao;
	@Resource
	CommunityServiceManager communityServiceManager;
	
	
	@Rollback(false)
	public void testAddCommunity() throws ParseException{
		Organization upOrg = (Organization)communityDao.getObject(Organization.class, 5);
		Organization organization = new Organization();
		organization.setOrgName("世贸雅苑");
		organization.setOrgType(BaseConstants.ORG_TYPE_COMMUNITY);
		organization.setUpOrgId(5);
		organization.setStatusCd(BaseConstants.STATUS_VALID);
		organization.setStatusDate(DateUtil.getCurTime());
		organization.setCreateDate(DateUtil.getCurTime());
		organization.setUpdateDate(DateUtil.getCurTime());
		
		PropertyCompany propertyCompany = (PropertyCompany) communityDao.getObject(PropertyCompany.class, 5);
		Address communityAddress = new Address();
		Address leaderAddress = new Address();
		Area area = (Area) communityDao.getObject(Area.class, 110101);
		communityAddress.setArea(area);
		communityAddress.setDetailAddress("东城区12号");
		leaderAddress.setArea(area);
		leaderAddress.setDetailAddress("东城区12号");
		
		Community community = new Community();
		community.setCommunityName("世贸雅苑");
		community.setOrganization(organization);
		community.setPropertyCompany(propertyCompany);
		
		PartyInfo leaderInfo = new PartyInfo();
		leaderInfo.setPartyName("世贸雅苑负责人1号");
		leaderInfo.setMobile("18998766789");
		leaderInfo.setAddress(leaderAddress);
		community.setPartyInfo(leaderInfo);
		
		PartyInfo communityInfo = new PartyInfo();
		communityInfo.setPartyName("世贸雅苑1号");
		communityInfo.setOfficePhone("08986756789");
		communityInfo.setAddress(communityAddress);
		community.setCommunityInfo(communityInfo);
		
		
		communityServiceManager.save(community);
	}
	
	
	@Rollback(false)
	public void testGetCommunityByName() throws ParseException{
		PageInfo page = communityDao.getCommunityByName("世贸", true, 1, 3);
		System.out.println("");
	}
	
	
	public void testGetCommunityCount(){
		Map<String,Object> map = new HashMap<String,Object>();
		int c = communityServiceManager.getCommunityCount(map);
		
	}
	
	@Test
	public void testGetCommunityInfo(){
		PartyInfo p = communityServiceManager.getPartyInfo(4);
		System.out.println("");
	}
}
