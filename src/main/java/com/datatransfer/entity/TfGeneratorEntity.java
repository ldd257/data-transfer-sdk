package com.datatransfer.entity;

import com.cngc.boot.web.dictionary.translate.DictTranslator;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@DictTranslator(value = "生成器类型")
	private String generatorType;
	/**
	 * 数据类型
	 */
	private String dataType;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
	private Date createTime;
	/**
	 * 状态
	 */
	@DictTranslator(value = "状态编码")
	private String status;

}
