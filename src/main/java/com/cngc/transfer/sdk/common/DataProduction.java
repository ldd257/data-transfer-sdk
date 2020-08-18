package com.cngc.transfer.sdk.common;

import cn.hutool.json.JSONUtil;
import com.datatransfer.entity.TfProductEntity;
import com.datatransfer.form.GeneratorForm2;
import com.datatransfer.form.PackageForm;

import java.util.HashMap;
import java.util.Map;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
public class DataProduction {

    private MyRequestUtils myRequestUtils = new MyRequestUtils();

    private TfProductEntity tfProductEntity;
    private GeneratorForm2 generatorForm;
    private  DataProduction(){}
    public DataProduction(GeneratorForm2 generatorForm, TfProductEntity tfProductEntity){
        this.tfProductEntity = tfProductEntity;
        this.generatorForm = generatorForm;

    }
    /**
     *
     */
    public DataPackage packaging(PackageForm packageBean){

        /*// 获取地址

        // 下载文件

        // 打包

        // 上传

        // 返回url*/


        packageBean.setProductId(tfProductEntity.getId().toString());
        Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> hashMap2 = new HashMap<>();
        Map<String, Object> hashMap3 = new HashMap<>();
        if (packageBean.getIsBroadcast().equals("true")){
            hashMap2.put("application_code",packageBean.getBroadcastReceiver().getApplicationCode());
            hashMap2.put("process_code",packageBean.getBroadcastReceiver().getProcessCode());
        }else {
            hashMap3.put("platform_code",packageBean.getReceivers().getPlatformCode());
            hashMap3.put("application_code",packageBean.getReceivers().getApplicationCode());
            hashMap3.put("process_code",packageBean.getReceivers().getProcessCode());
        }
        hashMap.put("is_broadcast",packageBean.getIsBroadcast());
        hashMap.put("receivers",hashMap3);
        hashMap.put("broadcast_receiver",hashMap2);
        hashMap.put("package_name",packageBean.getPackageName());
        hashMap.put("product_id",tfProductEntity.getId().toString());
        String result = myRequestUtils.myRequestPost("/transfer/products/"+ generatorForm.getGenerator_code() +"/packaged", hashMap);
        Map packageMap = JSONUtil.toBean(result, Map.class);
        hashMap.put("id", (Integer)packageMap.get("id"));
        String result2 = myRequestUtils.myRequestPost("/transfer/packages/"+ generatorForm.getGenerator_code() +"/packing", hashMap);


        // bean 上传 获取url
        // 返回json

        // 保存数据生成
        return new DataPackage(generatorForm, tfProductEntity.getId(), result2);
    }
}
