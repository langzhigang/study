package cn.lzg.test;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:configs/spring/applicationContext*.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {
	public BaseTest() {
		PropertyConfigurator.configure(BaseTest.class.getClassLoader()
				.getResource("configs/log4j.properties"));
	}

}
