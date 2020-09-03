
package com.cngc.transfer.sdk.common;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.form.GeneratorBean;
import com.cngc.transfer.sdk.form.GeneratorForm;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
@Component
public class DataGeneratorContext {


    private MyRequestUtils myRequestUtils = new MyRequestUtils();

    /**
     * 获得实例
     */
    public DataGenerator getGenerator(String code) {
        // 1,查询生成器
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + code);
        DataGenerator dataGenerator = JSONUtil.toBean(result, DataGenerator.class);
        dataGenerator.setGeneratorCode(dataGenerator.getGeneratorCode());
        dataGenerator.setMyRequestUtils(myRequestUtils);
        return dataGenerator;
    }

    /**
     * 创建生成器.
     */
    public GeneratorBean createGenerator(GeneratorBean generatorBean) {
        GeneratorForm generatorForm = new GeneratorForm();
        generatorForm.setGenerator_code(generatorBean.getGeneratorCode());
        generatorForm.setSequence_code(generatorBean.getSequenceCode());
        generatorForm.setGenerator_type(generatorBean.getGeneratorType());
        String generatorJson = JSONUtil.toJsonStr(generatorForm);
        String result = myRequestUtils.myRequestPost("/transfer/generators",generatorJson);
        JSONObject parseObj = JSONUtil.parseObj(result);
        generatorBean.setGeneratorType(parseObj.getStr("generator_type"));
        generatorBean.setDatas(parseObj.getStr("datas"));
        generatorBean.setStatus(parseObj.getStr("status"));
        return generatorBean;
    }


}
