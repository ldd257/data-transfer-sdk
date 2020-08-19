/**
 * Copyright (c) 中国兵器 All rights reserved.
 *
 * norincogroup
 *
 * 版权所有，侵权必究！
 */

package com.cngc.transfer.sdk.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据包裹
 *
 * @author ldd
 */
@Data
public class PackageForm {

    /**
     * 接收者
     */
    private List<Receiver> receivers;

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



    public void setReceivers(Receiver receivers) {
        List<Receiver> rece = new ArrayList<>();
        rece.add(receivers);
        this.receivers = rece;
    }
}
