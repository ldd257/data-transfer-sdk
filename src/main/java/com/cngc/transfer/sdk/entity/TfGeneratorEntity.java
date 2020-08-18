package com.cngc.transfer.sdk.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 生成器表
 * 
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:15
 */
@Data
public class TfGeneratorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 生成器编码
	 */
	private String generatorCode;
	/**
	 * 序列类型
	 */
	private String orderTypeCode;
	/**
	 * 生成器类型(01:普通;02:流式)
	 */
	private String generatorType;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态
	 */
	private String status;

}
