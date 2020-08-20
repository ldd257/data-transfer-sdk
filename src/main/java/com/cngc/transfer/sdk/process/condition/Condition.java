package com.cngc.transfer.sdk.process.condition;

/**
 * 处理包裹时,需要满足的条件.
 *
 * @author maxD
 */
public interface Condition {

    /**
     * 与逻辑.
     *
     * @param condition 条件
     * @return 增加后的条件
     */
    default Condition and(Condition condition) {
        return null;
    }

    /**
     * 或逻辑.
     *
     * @param condition 条件
     * @return 合并后的条件
     */
    default Condition or(Condition condition) {
        return null;
    }
}
