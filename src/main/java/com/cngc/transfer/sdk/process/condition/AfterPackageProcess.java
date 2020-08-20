package com.cngc.transfer.sdk.process.condition;

import java.util.ArrayList;
import java.util.List;

/**
 * 在某些包裹处理完成后才能进行处理.
 *
 * @author maxD
 */
public class AfterPackageProcess implements Condition {
    private List<String> packageIds = new ArrayList<>();

    /**
     * 添加包裹id.
     *
     * @param packageId 包裹id
     * @return this条件对象
     */
    public AfterPackageProcess addPackageId(String packageId) {
        this.packageIds.add(packageId);
        return this;
    }
}
