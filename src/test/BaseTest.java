package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:WebRoot/WEB-INF/applicationContext-*.xml","file:WebRoot/WEB-INF/applicationContext.xml"})
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	
}
