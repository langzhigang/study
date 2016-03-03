package cn.lzg.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.entity.Author;
import com.lzg.helper.PageHelper;
import com.lzg.service.IAuthorService;

/**
 * 
 * @author lzg
 *  mybatis高级特性的测试
 */
public class MybatisTest2 extends BaseTest {

	@Autowired
	private IAuthorService authorService;
	
	//测试高级查询，one to many
	@Test
	public void testAdvancedSelect(){
//		List<Author> findAll = authorService.findAll();
//		System.out.println(findAll);
		
		List<Author> findAllByJoin = authorService.findAllByJoin();
		System.out.println(findAllByJoin);
	}
	
	//测试 分页查询
	@Test
	public void testPageQuery(){
		int currentPageNo = 1;
		int pageSize = 2;
		Map<String,Object> params = new HashMap<String,Object>();
		PageHelper pageHelper = new PageHelper(currentPageNo,pageSize);
		params.putAll(pageHelper.toParamsMap());
		
		List<Author> list = authorService.findAllPage(params);
		int totalPageNo = authorService.findAllPageCount(params);
		pageHelper.setTotalPageNo(totalPageNo);
		
		System.out.println(list.size() + " : "+totalPageNo);
	}
}