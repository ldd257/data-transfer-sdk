/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.datatransfer.form;

import com.cngc.boot.web.dictionary.translate.DictTranslator;
import lombok.Data;

/**
 * data
 *
 * @author ldd
 */
@Data
public class DataForm {

    @DictTranslator(value = "数据类型")
    private String data_type_code;

    private String value;

}
