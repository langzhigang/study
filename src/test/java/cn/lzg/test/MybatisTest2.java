package cn.lzg.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.entity.Author;
import com.lzg.entity.TestUser;
import com.lzg.entity.UUIDUser;
import com.lzg.helper.PageHelper;
import com.lzg.interceptor.PageParameter;
import com.lzg.result.dto.TestPage;
import com.lzg.service.IAuthorService;
import com.lzg.service.IUserServiceTest;

/**
 * 
 * @author lzg mybatis高级特性的测试
 */
public class MybatisTest2 extends BaseTest {

	@Autowired
	private IAuthorService authorService;

	@Autowired
	private IUserServiceTest userService;

	// 测试高级查询，one to many
	@Test
	public void testAdvancedSelect() {
		// List<Author> findAll = authorService.findAll();
		// System.out.println(findAll);

		List<Author> findAllByJoin = authorService.findAllByJoin();
		System.out.println(findAllByJoin);
	}

	// 测试 分页查询
	@Test
	public void testPageQuery() {
		int currentPageNo = 1;
		int pageSize = 2;
		Map<String, Object> params = new HashMap<String, Object>();
		PageHelper pageHelper = new PageHelper(currentPageNo, pageSize);
		params.putAll(pageHelper.toParamsMap());

		List<Author> list = authorService.findAllPage(params);
		int totalPageNo = authorService.findAllPageCount(params);
		pageHelper.setTotalPageNo(totalPageNo);

		System.out.println(list.size() + " : " + totalPageNo);
	}

	// 测试嵌套service调用时，动态数据源的选择
	@Test
	public void testNestService() {
		UUIDUser user = new UUIDUser();
		user.setUserName("哈哈NestService");
		user.setBirth(new Date());
		userService.testNestService(user);
	}

	@Test
	public void testPagePlugin() {
		TestPage testPage = new TestPage();

		PageParameter pageParameter = new PageParameter();

		pageParameter.setCurrentPage(1);
		pageParameter.setPageSize(2);
		testPage.setPage(pageParameter);

		testPage.setTestUser(null);
		testPage.setTestUser(null);

		testPage.setOrderBy("userName");

		List<TestUser> users = userService.findUserPage(testPage);
		System.out.println(users);
	}

}
