/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk.form;

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
     * 包名
     */
    private String packageName;

    /**
     * 包code
     */
    private String code;
    /**
     * 文件url
     */
    private String fileUrl;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd'T'HH-mm-ss.SSSXXX")
    private String createTime;

}
