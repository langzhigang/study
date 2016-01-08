package com.lzg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzg.entity.UUIDUser;
import com.lzg.entity.User;

public interface IUserDao {
	public List<User> findAll();

	public boolean save(User user);

	public void update(User user);

	public void delete(User user);

	public User findUserById(long userId);

	//返回插入的条数
	public int batchSaveUser(@Param("users") List<User> users);

	public void saveUUIDUser(UUIDUser user);
	
}
