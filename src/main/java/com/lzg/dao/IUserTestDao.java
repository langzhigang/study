package com.lzg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lzg.entity.TestUser;
import com.lzg.entity.UUIDUser;
import com.lzg.entity.UserTest;
import com.lzg.result.dto.TestPage;

public interface IUserTestDao {
	public List<UserTest> findAll();

	public boolean save(UserTest user);

	public void update(UserTest user);

	public void delete(UserTest user);

	public UserTest findUserById(long userId);

	// 返回插入的条数
	public int batchSaveUser(@Param("users") List<UserTest> users);

	public void saveUUIDUser(UUIDUser user);

	public UserTest validate(@Param("userName") String userName, @Param("password") String password);

	public List<TestUser> findUserPage(TestPage testPage);

	public List<String> findD();

	public void insertD(@Param("list") List<Map<String, String>> list);

}
