package com.cngc.transfer.sdk.process.condition;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在某些包裹处理完成后才能进行处理.
 *
 * @author maxD
 */
public class AfterPackageProcess implements Condition {
    //    public List<String> packageIds = new ArrayList<>();
    public Map<String, String> map = new HashMap<>();

    public AfterPackageProcess(){
        map.put("AfterPackageProcess","");
    }
    /**
     * 添加包裹id.
     *
     * @param packageId 包裹id
     * @return this条件对象
     */
    public AfterPackageProcess addPackageId(String packageId) {
//        this.packageIds.add(packageId);
        map.put("AfterPackageProcess",map.get("AfterPackageProcess").concat(",").concat(packageId));
        return this;
    }

    public String getCondition(){
        map.put("AfterPackageProcess",JSONUtil.toJsonStr(map.get("AfterPackageProcess")).substring(1));
        return JSONUtil.toJsonStr(map);
    }

}
