/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.datatransfer.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 数据包裹
 *
 * @author ldd
 */
@Data
public class PackageBean {

    /**
     * 接收者
     */
    private Receivers receivers;

    /**
     * 广播接收者
     */
    private BroadcastReceiver broadcastReceiver;

    /**
     * 是否广播
     */
    private String isBroadcast;

    /**
     * 包名
     */
    private String packageName;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
    private Date createTime;

    /**
     * 产品主键
     */
    private String productId;
}
