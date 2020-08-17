package com.datatransfer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 序列表
 * 
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-16 10:08:19
 */
@Data
public class TfSequenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@JsonIgnore
	private Long id;
	/**
	 * 序列编码
	 */
	private String seqCode;
	/**
	 * 序列名称
	 */
	private String seqName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 当前序列号
	 */
	private String currentNum;
	/**
	 * 间隔序列号
	 */
	private String intervalNums;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
	private Date createTime;

}
