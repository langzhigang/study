package cn.lzg.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:configs/spring/applicationContext*.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {
	public BaseTest() {
	}
}
