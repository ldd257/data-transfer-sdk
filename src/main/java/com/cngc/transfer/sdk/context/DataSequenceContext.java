package com.cngc.transfer.sdk.context;

import com.cngc.transfer.sdk.common.DataSequence;

/**
 * 数据序列上下文,通过此类完成数据序列的curd.
 *
 * @author maxD
 */
public class DataSequenceContext {

    /**
     * 通过数据序列编码获取对应的数据序列.
     *
     * @param code 数据序列编码
     * @return 数据序列对象
     */
    public DataSequence getDataSequence(String code) {
        // TODO 实现根据数据序列编码获取数据序列的逻辑
        return null;
    }

    /**
     * 创建新的数据序列.
     *
     * @param dataSequence 数据序列对象
     * @return 创建出的数据序列对象.
     */
    public DataSequence createDataSequence(DataSequence dataSequence) {
        // TODO 实现创建数据序列逻辑
        return null;
    }
}
