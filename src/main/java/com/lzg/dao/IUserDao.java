package com.lzg.dao;

import java.util.List;

import com.lzg.entity.User;

public interface IUserDao {
	public List<User> findAll();

	public void save(User user);

	public void update(User user);

	public void delete(User user);

	public User findUserById(long userId);
	
}
