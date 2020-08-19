package com.cngc.transfer.sdk.common;

import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.entity.TfProductEntity;
import com.cngc.transfer.sdk.entity.TfSequenceEntity;
import com.cngc.transfer.sdk.form.DataForm;
import com.cngc.transfer.sdk.form.GeneratorForm;
import com.cngc.transfer.sdk.form.GeneratorForm2;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
@Component
public class DataGenerator {

    @Autowired
    private DataGeneratorContext dataGeneratorContext;
    @Autowired
    private DataProduction dataProduction;
    @Autowired
    private MyRequestUtils myRequestUtils;

    /**
     * 数据序列.
     */
    @Autowired
    private DataSequence dataSequence;

    private GeneratorForm2 generatorForm;

    public DataGenerator(){}


    /**
     * 数据追加
     */
    private void appendJson(String json){
        generatorForm = dataGeneratorContext.getGeneratorForm();
        // 数据 listObj
        String fileUrl = myRequestUtils.myRequestPost("/transfer/oss/upload", json);
        DataForm dataForm = new DataForm();
        dataForm.setData_type_code("01");
        dataForm.setValue(fileUrl);

        // 上传

        // 修改datas
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + generatorForm.getGenerator_code());
        GeneratorForm generatorForm = JSONUtil.toBean(result, GeneratorForm.class);

        generatorForm.getDatas().add(dataForm);
        myRequestUtils.myRequestPut("/transfer/generators/"+ generatorForm.getGeneratorCode(), generatorForm);
    }

    public void appendFile(File file){

        // 上传
        HashMap<String, Object> paramMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("file", file);
        String fileUrl = myRequestUtils.myRequestPost("/transfer/oss/upload", paramMap);

        // 修改datas


    }
    public void appendObject(Object obj){

        this.appendJson(JSONUtil.toJsonStr(obj));


    }

    public void append(Object obj){
        this.appendJson(JSONUtil.toJsonStr(obj));
    }

    /**
     * 数据生成
     */
    public DataProduction build(){
        // 获取datasUrls

        // 下载文件

        // 加密 TODO

        //


        // 当前序列号
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + generatorForm.getGenerator_code());
        Map map1 = JSONUtil.toBean(result, Map.class);
        String result2 = myRequestUtils.myRequestGet("/transfer/sequences/" + map1.get("order_type_code"));
        Map map2 = JSONUtil.toBean(result2, Map.class);
        TfSequenceEntity tfSequenceEntity = new TfSequenceEntity();
        Integer currentNumed = map2.get("current_num") == null || map2.get("current_num").equals("") ? 0 : Integer.parseInt((String) map2.get("current_num"));
        if (dataSequence.getFlag() == 1){
            // flag = 1
            // 序列号+1
            // 修改 追加+1后的序列号

            tfSequenceEntity.setCurrentNum(String.valueOf(currentNumed + 1) );
            tfSequenceEntity.setIntervalNums(currentNumed + ",");
        }else{
            // flag = 2
            String replace = null;
            try {
                replace = ((String) map2.get("interval_nums")).replace(currentNumed + ",", "");
            }catch (Exception e){
                replace = "";
            }
            tfSequenceEntity.setIntervalNums(replace);
        }
        tfSequenceEntity.setSeqName((String) map2.get("seq_name"));
        tfSequenceEntity.setDescription((String) map2.get("description"));
        myRequestUtils.myRequestPut("/transfer/sequences/"+((String) map2.get("seq_code")), tfSequenceEntity);

        // 1,随机密钥加密

        TfProductEntity product = new TfProductEntity();
        product.setGeneratorType(generatorForm.getGenerator_type().getCode());
        product.setGeneratorCode(generatorForm.getGenerator_code());

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("product_name","autoProductName");
        // 保存数据生成
        String productM = myRequestUtils.myRequestPost("/transfer/generators/" + generatorForm.getGenerator_code() + "/builded", hashMap);
        Map productMap = JSONUtil.toBean(productM, Map.class);
        product.setId(Long.parseLong(((Integer)productMap.get("id")).toString()));
        dataProduction.setGeneratorForm(generatorForm);
        dataProduction.setTfProductEntity(product);
        return dataProduction;
    }


}
