package com.hellowd.server;

import com.hellowd.server.api.repository.StoreOwner;
import com.hellowd.server.api.repository.StoreOwnerRepository;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloworldInfraCidPushServerBootApplication.class)
@Transactional
public class HelloworldInfraCidPushServerBootApplicationTests {

	private static Logger logger = Logger.getLogger(HelloworldInfraCidPushServerBootApplicationTests.class);

	@Autowired
	private StoreOwnerRepository storeOwnerRepository;

	@Test
	public void 테스트seq키값찾기() throws Exception{
		//5번 사용자 이름 찾기
		StoreOwner storeOwner = storeOwnerRepository.findBySeq(5L);
		assertTrue(storeOwner.getName().equals("헬로월드"));
		logger.info(storeOwner.getName());
	}


}
