package cn.lzg.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.entity.UUIDUser;
import com.lzg.entity.User;
import com.lzg.service.IUserService;

/**
 * 
 * @author lzg
 *  mybatis的基本测试
 */
public class MybatisTest1 extends BaseTest {

	@Autowired
	private IUserService userService;

	// 测试对user类的 CRUD
	@Test
	public void testCRUD() {
		User user = new User();
		user.setUserName("保存");
		user.setPassword("123");
		userService.saveUser(user);
		System.out.println("------save 完成 -----------");
//		User u1 = userService.findUserById(user.getUserId());
//		System.out.println("查询保存后的数据：" + u1.toString());

//		u1.setUserName("更新");
//		userService.updateUser(u1);
//		User u2 = userService.findUserById(u1.getUserId());
//		System.out.println("查询更新后的数据：" + u2.toString());

//		List<User> l1 = userService.findAll();
//		System.out.println("删除数据前查询：" + l1.size());
//		userService.deleteUser(u2);
//		List<User> list = userService.findAll();
//		System.out.println("删除数据后查询：" + list.size());
	}

	//测试批量插入
	@Test
	public void testBatchInsert(){
		List<User> users = new ArrayList<User>();
		User user;
		for(int i=0;i<1000;i++){
			user = new User();
			user.setUserName("name_" + i);
			user.setPassword("123456");
			users.add(user);
		}
		long start = System.currentTimeMillis();
		
		//插入上面1000条记录的测试
		
		//耗时 22930 17379 18465(普通for循环，一条一条插入)
//		userService.batchSaveUser1(users);	
		
		//耗时 5595 6349 6575(每次提交10条)
		//耗时 5200 3273 3588(每次提交20条)		
		for(int i=0, len=users.size(); i<len; i=i+20){	
			int end = i+20 < len ? i+20 : len;
			List<User> temp = users.subList(i, end);
			userService.batchSaveUser(temp);
		}
		
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	//测试返回uuid主键
	@Test
	public void testUUIDInsert(){
		UUIDUser user = new UUIDUser();
		user.setUserName("哈哈");
		userService.saveUUIDUser(user);
		System.out.println(user.getUserId());
	}
}
