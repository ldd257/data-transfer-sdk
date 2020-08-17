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
public class GeneratorForm2 {

    private DataForm2 generator_type;

    private String order_type_code;

    private String generator_code;

    private List<DataForm> datas;


    private DataForm2 status;



}
