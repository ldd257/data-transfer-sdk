
package com.cngc.transfer.sdk.common;



import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.form.GeneratorForm2;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataGenerator dataGenerator;
    @Autowired
    private MyRequestUtils myRequestUtils;

    /**
     * 数据序列.
     */
    @Autowired
    private DataSequence dataSequence;

    private GeneratorForm2 generatorForm;


    /**
     * 获得实例
     */
    public   DataGenerator getGenerator(String code){
        // 1,查询生成器
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + code);
        generatorForm = JSONUtil.toBean(result, GeneratorForm2.class);
        dataGenerator.setGeneratorForm(generatorForm);
        return dataGenerator;
    }



}
