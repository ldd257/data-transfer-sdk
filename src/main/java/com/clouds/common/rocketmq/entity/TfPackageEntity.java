package com.clouds.common.rocketmq.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 包裹表
 * 
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:15
 */
@Data
public class TfPackageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 包裹名称
	 */
	private String packageName;
	/**
	 * 是否广播
	 */
	private String isBroadcast;
	/**
	 * 接收应用编码
	 */
	private String receivers;
	/**
	 * 产品名称
	 */
	private String broadcastReceiver;
	/**
	 * 文件url
	 */
	private String fileUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 产品主键
	 */
	private String productId;

}
