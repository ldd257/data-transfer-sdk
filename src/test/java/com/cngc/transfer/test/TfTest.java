package com.cngc.transfer.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.entity.TfProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductPackageTest.class)
public class TfTest {

  @Autowired
  private MyRequestUtils myRequestUtils;

  @Test
  public void httpGetTest(){

    String result = myRequestUtils.myRequestGet("/transfer/generators/GE00001");
//    String result = HttpUtil.get("http://localhost:8080/transfer/generators/GE00001");
    System.out.println(result);
  }

  @Test
  public void httpPostTest(){
    TfProductEntity tf = new TfProductEntity();
    tf.setProductName("postTest");
    // 保存数据生成
    String result = HttpUtil.post("http://localhost:8080/transfer/generators/ss2/builded", JSONUtil.toJsonStr(tf));
    System.out.println(result);
  }

  @Test
  public void httpPostTest2(){
    TfProductEntity tf = new TfProductEntity();
    tf.setProductName("postTest");
    // 保存数据生成
    String result = myRequestUtils.myRequestPost("/transfer/generators/ss2/builded", tf);
    System.out.println(result);
  }

  @Test
  public void httpPostTest3(){
    HashMap<String, Object> paramMap = new HashMap<>();
    //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
    paramMap.put("file", FileUtil.file("D:\\yun_platform\\yun2.sql"));

    String post = HttpUtil.post("http://localhost:8080/transfer/oss/upload", paramMap);

    System.out.println(post);
  }
  @Test
  public void httpPostTest4(){
    //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别

//    String post = HttpUtil.post("http://localhost:8080/transfer/oss/upload", paramMap);
//    HttpUtil.createRequest(Method.PUT, "http://localhost:8080/transfer/sequences/efdf3d4b-62ad-4c54-ba94-4a1b15e13111").execute().body();
    /*{
      "seq_name":"seqTest",
        "description":"descriptiondescription",
        "seq_type":"1",
        "current_num":"2",
        "interval_nums":""
    }*/
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("seq_name", "seqTest");
    hashMap.put("description", "descriptiondescription");
    hashMap.put("current_num", "3");
    hashMap.put("interval_nums", "");
    String result = HttpRequest.put("http://localhost:8080/transfer/sequences/efdf3d4b-62ad-4c54-ba94-4a1b15e13111")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body(JSONUtil.toJsonStr(hashMap))
        .execute().body();
    System.out.println(result);
  }
  @Test
  public void httpPostTest5(){
    String fileUrl = HttpRequest.put("http://localhost:8080/transfer/sequences/efdf3d4b-62ad-4c54-ba94-4a1b15e13111")
        .header("Content-Type", "application/x-www-form-urlencoded")
        .body("httpPostTest5httpPostTest5httpPostTest5")
        .execute().body();


    System.out.println(fileUrl);
  }
}
