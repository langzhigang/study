package cn.lzg.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzg.entity.User;
import com.lzg.service.IUserService;

public class Test1 extends BaseTest {

	@Autowired
	private IUserService userService;

	// 测试对user类的 CRUD
	@Test
	public void testCRUD() {
		User user = new User();
		user.setUserName("保存");
		user.setPassWord("123");
		userService.saveUser(user);
		System.out.println("------save 完成 -----------");
		User u1 = userService.findUserById(1);
		System.out.println("查询保存后的数据：" + u1.toString());

		u1.setUserName("更新");
		userService.updateUser(u1);
		User u2 = userService.findUserById(u1.getId());
		System.out.println("查询更新后的数据：" + u2.toString());

//		List<User> l1 = userService.findAll();
//		System.out.println("删除数据前查询：" + l1.size());
//		userService.deleteUser(u2);
//		List<User> list = userService.findAll();
//		System.out.println("删除数据后查询：" + list.size());
	}
	
}
