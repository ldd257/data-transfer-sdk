/**
 * Copyright (c) 中国兵器 All rights reserved.
 * <p>
 * norincogroup
 * <p>
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk.form;

import com.cngc.transfer.sdk.process.condition.Condition;
import lombok.Data;

@Data
public class BroadcastReceiver {

    private String applicationCode;

    private String processCode;

    private Condition condition;
}