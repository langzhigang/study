package com.lzg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lzg.dao.ITestDao;
import com.lzg.service.ITestService;
import com.lzg.service.ITestServiceB;

@Service("testService")
@Transactional
public class TestServiceImpl implements ITestService {

	@Autowired
	ITestDao testDao;

	@Autowired
	ITestServiceB testServiceB;

	@Override
	public void a() {
		testDao.insert(1);
		// try {
		// b();
		// } catch (Exception e) {
		// throw new RuntimeException("哈哈");
		// }
		// testServiceB.b();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
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
			if (name.equals("c")) {
				// throw new RuntimeException("哈哈");
			}
			testDao.insertOne(1, name);
		}

		// testDao.batchInsert(p);
	}

}
