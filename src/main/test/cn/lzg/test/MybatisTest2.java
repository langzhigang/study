package cn.lzg.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.entity.Author;
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
		List<Author> findAll = authorService.findAll();
		System.out.println(findAll);
		
//		List<Author> findAllByJoin = authorService.findAllByJoin();
//		System.out.println(findAllByJoin);
	}
	
}
