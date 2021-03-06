/**
 * Copyright (c) 中国兵器 All rights reserved.
 * <p>
 * norincogroup
 * <p>
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk.form;

import lombok.Data;

@Data
public class Receiver {
    private String platformCode;

    private String applicationCode;

    private String processCode;
    private Boolean ignore = false;
}

