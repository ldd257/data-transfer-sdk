/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk.form;


import lombok.Data;

/**
 * 应用表单
 *
 * @author ldd
 */
@Data
public class GeneratorForm {
    private String generator_type;
    private String sequence_code;
    private String generator_code;
    private String datas;
    private String data_json;
    private String status;


}
