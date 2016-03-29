package test.wuye;

import com.wuye.entity.AttrValue;
import com.wuye.services.AttrValueService;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import test.BaseTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TestAttrValue extends BaseTest {
	@Resource
	AttrValueService attrValueService;
	
	@Test
	@Rollback(false)
	public void testGetAttrValue(){
		AttrValue attrValue = attrValueService.getAttrValue(12, "right_type", "福利房");
		AttrValue attrValue2 = attrValueService.getAttrValue(12, "right_type", 4, "特价房");
		List<AttrValue> attrValues = attrValueService.getAttrValue(12, "right_type", 4, true);
		List<AttrValue> attrValues2 = attrValueService.getAttrValue(12, "right_type", 4, false);
		assertNotNull(attrValue);
		assertNotNull(attrValue2);
		assertNotNull(attrValues);
		assertNotNull(attrValues2);
	}
}
