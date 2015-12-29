package com.lzg.service;

import java.util.List;

import com.lzg.entity.UUIDUser;
import com.lzg.entity.User;

public interface IUserService {
	public List<User> findAll();

	public User findUserById(long userId);

	public boolean saveUser(User user);

	public boolean deleteUser(User user);

	public boolean updateUser(User user);
	
	//在sql里面进行批量
	public boolean batchSaveUser(List<User> users);
	
	//在service里面用for，然后一条一条插入
	public boolean batchSaveUser1(List<User> users);
	
	public boolean saveUUIDUser(UUIDUser user);
	
}
