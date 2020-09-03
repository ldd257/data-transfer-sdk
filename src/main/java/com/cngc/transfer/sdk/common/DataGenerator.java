package com.cngc.transfer.sdk.common;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.form.DataForm;
import com.cngc.transfer.sdk.form.GeneratorForm;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
public class DataGenerator {

    private MyRequestUtils myRequestUtils = new MyRequestUtils();

    private String generatorCode;
    /**
     * 生成器server 上下文
     */
    private static final String DATA_CONTEXT  = "/transfer/generators/";

    /**
     * 追加实体，字符串，数字......
     * @param obj
     */
    public void append(Object obj) {
        byte[] bytes = JSONUtil.toJsonStr(obj).getBytes();
        this.upload(bytes, "");
    }

    /**
     * 数据文件流.
     * 文件类型.
     */
    public void append(InputStream in, String suffix) {
        byte[] bytes = IoUtil.readBytes(in);
        this.upload(bytes, suffix);
    }

    /**
     * 文件上传
     * @param bytes
     * @param suffix
     */
    private void upload(byte[] bytes, String suffix) {
        Map<String, String> map = new HashMap<>();
        map.put("data",Base64.encodeBase64String(bytes));
        map.put("name",suffix);
        // 数据 listObj
        String uuid = myRequestUtils.myRequestPost("/transfer/oss/uploadByte", JSONUtil.toJsonStr(map));
        DataForm dataForm = new DataForm();
        dataForm.setCode("default");
        dataForm.setData_content(uuid);
        dataForm.setData_type(suffix);
        // 查询生成器
        String result = myRequestUtils.myRequestGet(DATA_CONTEXT + generatorCode);
        GeneratorForm generatorForm = JSONUtil.toBean(result, GeneratorForm.class);
        // 追加数据信息
        List<DataForm> objects = JSONUtil.parseArray(generatorForm.getDatas()).toList(DataForm.class);
        objects.add(dataForm);
        generatorForm.setData_json(JSONUtil.toJsonStr(objects));
        myRequestUtils.myRequestPut(DATA_CONTEXT+ generatorForm.getGenerator_code(), generatorForm);
    }

    /**
     * 数据产品生成
     */
    public DataProduct build() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("product_name","autoProductName");
        // 保存数据生成
        String productM = myRequestUtils.myRequestPost(DATA_CONTEXT + generatorCode + "/builded", hashMap);
        JSONObject jsonObject1 = JSONUtil.parseObj(productM);
        DataProduct producted = jsonObject1.toBean(DataProduct.class);
        producted.setGeneratorCode(generatorCode);
        producted.setMyRequestUtils(myRequestUtils);
        return producted;
    }

    /**
     * 查询序列.
     * @return
     */
    public DataSequence getSequence() {
        String seq = myRequestUtils.myRequestGet(DATA_CONTEXT + generatorCode);
        return JSONUtil.toBean(seq, DataSequence.class);
    }
}
