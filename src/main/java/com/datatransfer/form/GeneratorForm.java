/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.datatransfer.form;

import com.cngc.boot.web.dictionary.translate.DictTranslator;
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

    @DictTranslator(value = "生成器类型")
    private String generatorTypeCode;

    private String orderTypeCode;

    private String generatorCode;

    private List<DataForm> datas;

    @JsonIgnore
    private String dataJson;

    @DictTranslator(value = "状态编码")
    private String statusCode;

    @JsonIgnore
    private Date createTime;


}
