/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.cngc.transfer.sdk")
public class TransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferApplication.class, args);
	}
}