package com.lzg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dao.IUserTestDao;
import com.lzg.entity.UUIDUser;
import com.lzg.entity.UserTest;
import com.lzg.service.IUserServiceTest;

@Service("userServiceTest")
public class UserServiceTest implements IUserServiceTest {

	@Autowired
	private IUserTestDao userDao;

	// 默认是RuntimeException 才会回滚
//	@Transactional(rollbackFor = { Exception.class })
	@Transactional(readOnly = true)
	public List<UserTest> findAll() {
		return userDao.findAll();
	}

	@Transactional(readOnly = true)
	public UserTest findUserById(long userId) {
		return userDao.findUserById(userId);
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean saveUser(UserTest user) {
		userDao.save(user);
		return true;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean deleteUser(UserTest user) {
		userDao.delete(user);
		// int i = 1 / 0; 测试事物
		return true;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean updateUser(UserTest user) {
		userDao.update(user);
		return true;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public boolean batchSaveUser(List<UserTest> users){
		userDao.batchSaveUser(users);
		return true;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean batchSaveUser1(List<UserTest> users) {
		for(int i=0, len=users.size(); i<len; i++){
			userDao.save(users.get(i));
		}
		return false;
	}

	@Transactional(rollbackFor = { Exception.class })
	public boolean saveUUIDUser(UUIDUser user) {
		userDao.saveUUIDUser(user);
		return false;
	}

}
