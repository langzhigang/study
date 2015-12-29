package com.lzg.service;

import java.util.List;

import com.lzg.entity.User;

public interface IUserService {
	public List<User> findAll();

	public User findUserById(long userId);

	public boolean saveUser(User user);

	public boolean deleteUser(User user);

	public boolean updateUser(User user);
	
	public boolean batchSaveUser(List<User> users);
}
