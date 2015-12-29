package com.lzg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dao.IUserDao;
import com.lzg.entity.UUIDUser;
import com.lzg.entity.User;
import com.lzg.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	// 默认是RuntimeException 才会回滚
	// @Transactional(rollbackFor = { Exception.class })
	@Transactional(rollbackFor = { Exception.class })
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Transactional(readOnly = true)
	public User findUserById(long userId) {
		return userDao.findUserById(userId);
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean saveUser(User user) {
		userDao.save(user);
		return true;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean deleteUser(User user) {
		userDao.delete(user);
		// int i = 1 / 0; 测试事物
		return true;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean updateUser(User user) {
		userDao.update(user);
		return true;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public boolean batchSaveUser(List<User> users){
		userDao.batchSaveUser(users);
		return true;
	}

	public boolean batchSaveUser1(List<User> users) {
		for(int i=0, len=users.size(); i<len; i++){
			userDao.save(users.get(i));
		}
		return false;
	}

	public boolean saveUUIDUser(UUIDUser user) {
		userDao.saveUUIDUser(user);
		return false;
	}

}
