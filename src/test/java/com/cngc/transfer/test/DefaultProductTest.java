package com.cngc.transfer.test;

import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.common.TransferConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultProductTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultProductTest.class);


	@Autowired
	private TransferConfig transferConfig;

	@Autowired
	private MyRequestUtils myRequestUtils;

	@Test
	public void test() {
		myRequestUtils.myRequestGet("123123123123");
		System.out.println(transferConfig.TRANFER_PLATFORM_URL);
	}


}
