package com.lzg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dao.ITestDao;
import com.lzg.service.ITestServiceB;

@Service("testServiceB")
@Transactional
public class TestServiceImplB implements ITestServiceB {

	@Autowired
	ITestDao testDao;

	@Override
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void b() {
		Map<String, Object> p = new HashMap<>();
		p.put("id", 1);
		List<String> names = new ArrayList<>();
		names.add("a");
		names.add("b");
		names.add("c");
		names.add("d");
		p.put("names", names);

		for (String name : names) {
			if ("c".equals(name)) {
				throw new RuntimeException("哈哈");
			}
			testDao.insertOne(1, name);
		}

		// testDao.batchInsert(p);
	}

}
