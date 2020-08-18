
package com.clouds.common.rocketmq.common;



import cn.hutool.json.JSONUtil;
import com.clouds.common.rocketmq.form.GeneratorForm2;
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

    MyRequestUtils myRequestUtils = new MyRequestUtils();

    /**
     * 数据序列.
     */
    private DataSeq dataSeq = new DataSeq();

    private static GeneratorForm2 generatorForm;


    /**
     * 获得实例
     */
    public   DataGenerator getGenerator(String code){
        // 1,查询生成器
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + code);
        generatorForm = JSONUtil.toBean(result, GeneratorForm2.class);

        return new DataGenerator(generatorForm);
    }



}
