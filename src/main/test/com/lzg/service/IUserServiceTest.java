package com.lzg.service;

import java.util.List;

import com.lzg.entity.UUIDUser;
import com.lzg.entity.UserTest;

public interface IUserServiceTest {
	public List<UserTest> findAll();

	public UserTest findUserById(long userId);

	public boolean saveUser(UserTest user);

	public boolean deleteUser(UserTest user);

	public boolean updateUser(UserTest user);
	
	//在sql里面进行批量
	public boolean batchSaveUser(List<UserTest> users);
	
	//在service里面用for，然后一条一条插入
	public boolean batchSaveUser1(List<UserTest> users);
	
	public boolean saveUUIDUser(UUIDUser user);
	
}
