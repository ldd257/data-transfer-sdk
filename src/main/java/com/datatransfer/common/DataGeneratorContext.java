package com.datatransfer.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import cn.hutool.json.JSONUtil;
import com.datatransfer.config.TransferConfig;
import com.datatransfer.entity.TfGeneratorEntity;
import com.datatransfer.entity.TfProductEntity;
import com.datatransfer.entity.TfSequenceEntity;
import com.datatransfer.form.DataForm;
import com.datatransfer.form.GeneratorForm;
import com.datatransfer.form.GeneratorForm2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
@Component
@SpringBootConfiguration
public class DataGeneratorContext {

    @Resource
    private MyRequestUtils myRequestUtils;

    /**
     * 数据序列.
     */
    private DataSeq dataSeq = new DataSeq();

    private String code;

    private static GeneratorForm2 generatorForm;

    private List<Object> listObj;

    public DataGeneratorContext(){}
    public DataGeneratorContext(String code){
        this.code = code;
    }

    private static   DataGeneratorContext instance = null;
    /**
     * 数据追加
     */
    public void appendJson(String json){

        // 数据 listObj
        String fileUrl = myRequestUtils.myRequestPost("/transfer/oss/upload", json);
        DataForm dataForm = new DataForm();
        dataForm.setData_type_code("01");
        dataForm.setValue(fileUrl);

        // 上传

        // 修改datas
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + generatorForm.getGenerator_code());
        GeneratorForm generatorForm = JSONUtil.toBean(result, GeneratorForm.class);
//        GeneratorForm generatorForm = new GeneratorForm();

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
//        TfGeneratorEntity tf = JSONUtil.toBean(result, TfGeneratorEntity.class);
        Map map1 = JSONUtil.toBean(result, Map.class);
        String result2 = myRequestUtils.myRequestGet("/transfer/sequences/" + map1.get("order_type_code"));
        Map map2 = JSONUtil.toBean(result2, Map.class);
        TfSequenceEntity tfSequenceEntity = new TfSequenceEntity();
//        TfSequenceEntity tfSeq = JSONUtil.toBean(result2, TfSequenceEntity.class);
        Integer currentNumed = map2.get("current_num") == null || map2.get("current_num").equals("") ? 0 : Integer.parseInt((String) map2.get("current_num"));
        if (dataSeq.getFlag() == 1){
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
        // sequenceNum
       /* product.setOrderTypeCode((String) map2.get("seq_code"));
        product.setProductName("autoProductName");*/

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("product_name","autoProductName");
        // 保存数据生成
        String productM = myRequestUtils.myRequestPost("/transfer/generators/" + generatorForm.getGenerator_code() + "/builded", hashMap);
        Map productMap = JSONUtil.toBean(productM, Map.class);
        product.setId(Long.parseLong(((Integer)productMap.get("id")).toString()));
        return new DataProduction(generatorForm,product);
    }
    /**
     * 获得实例
     */
    public   DataGeneratorContext getInstance(String code){
        // 1,查询生成器
        String result = myRequestUtils.myRequestGet("/transfer/generators/" + code);
        generatorForm = JSONUtil.toBean(result, GeneratorForm2.class);

        return new DataGeneratorContext();
    }

    public static void toZip(List<File> srcFiles , OutputStream out)throws RuntimeException {

        long start = System.currentTimeMillis();

        ZipOutputStream zos = null;

        try {

            zos = new ZipOutputStream(out);

            for (File srcFile : srcFiles) {

                byte[] buf = new byte[BUFFER_SIZE];

                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int len;

                FileInputStream in = new FileInputStream(srcFile);

                while ((len = in.read(buf)) != -1) {

                    zos.write(buf, 0, len);

                }

                zos.closeEntry();

                in.close();

            }

            long end = System.currentTimeMillis();

            System.out.println("压缩完成，耗时：" + (end - start) + " ms");

        } catch (Exception e) {

            throw new RuntimeException("zip error from ZipUtils", e);

        } finally {

            if (zos != null) {

                try {

                    zos.close();

                } catch (IOException e) {

                    e.printStackTrace();

                }

            }

        }
    }


}
