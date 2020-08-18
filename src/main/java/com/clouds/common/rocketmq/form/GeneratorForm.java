/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.clouds.common.rocketmq.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 应用表单
 *
 * @author ldd
 */
@Data
public class GeneratorForm {


    private String generatorTypeCode;

    private String orderTypeCode;

    private String generatorCode;

    private List<DataForm> datas;

    @JsonIgnore
    private String dataJson;

    private String statusCode;

    @JsonIgnore
    private Date createTime;


}
