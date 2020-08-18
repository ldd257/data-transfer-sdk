package com.clouds.common.rocketmq.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品表
 * 
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
public class TfProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 生成器编码
	 */
	private String orderTypeCode;
	/**
	 * 生成器编码
	 */
	private String generatorCode;
	/**
	 * 生成器类型(01:普通;02:流式)
	 */
	private String generatorType;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
	private Date createTime;

}
