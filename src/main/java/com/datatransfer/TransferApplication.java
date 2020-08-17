/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.datatransfer;

import com.cngc.boot.core.annotation.EnableCngcBoot;
import com.cngc.boot.web.annotation.EnableCngcWebMvc;
import com.cngc.boot.web.dictionary.translate.EnableDictTranslate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableCngcBoot
@EnableCngcWebMvc
@EnableDictTranslate
public class TransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferApplication.class, args);
	}
}