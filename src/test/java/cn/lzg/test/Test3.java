package cn.lzg.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.service.ITestService;

public class Test3 extends BaseTest {

	@Autowired
	ITestService testService;

	@Test
	public void test() {
		testService.a();
	}

}
