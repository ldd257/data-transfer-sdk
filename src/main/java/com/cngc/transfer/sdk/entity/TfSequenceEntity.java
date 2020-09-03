package com.cngc.transfer.sdk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("code")
	private String code;
	/**
	 * 序列名称
	 */
	@JsonProperty("seq_name")
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 当前序列号
	 */
	@JsonProperty("current_num")
	private String currentNum;
	/**
	 * 间隔序列号
	 */
	@JsonProperty("interval_nums")
	private String intervalNums;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
	@JsonProperty("create_time")
	private Date createTime;

	/**
	 * 序列操作类型.
	 */
	private int type;

	/**
	 * 序列跳号序列号.
	 */
	private int dataNum;


}
