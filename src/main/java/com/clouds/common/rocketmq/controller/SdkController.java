package com.clouds.common.rocketmq.controller;

import com.clouds.common.rocketmq.aspect.UserDemo;
import com.clouds.common.rocketmq.common.DataGenerator;
import com.clouds.common.rocketmq.common.DataGeneratorContext;
import com.clouds.common.rocketmq.common.DataGeneratorContextHolder;
import com.clouds.common.rocketmq.common.DataPackage;
import com.clouds.common.rocketmq.common.DataProduction;
import com.clouds.common.rocketmq.common.DataSeq;
import com.clouds.common.rocketmq.context.DataApplicationContextAware;
import com.clouds.common.rocketmq.form.DataForm;
import com.clouds.common.rocketmq.form.PackageBean;
import com.clouds.common.rocketmq.form.PackageForm;
import com.clouds.common.rocketmq.form.Receivers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SdkController {



  @GetMapping("/testtest")
  public void list(){
    Map maps = DataApplicationContextAware.getApplicationContext();
    DataGeneratorContext context = DataGeneratorContextHolder.getContext();
    DataGenerator dataGenerator = context.getGenerator("GE00001");

    // 模拟数据
    UserDemo datas = this.datas2();
//    DataForm datas = this.datas3();
    dataGenerator.append(datas);

    DataSeq dataSeq = dataGenerator.getDataSeq();


    dataSeq.skip();
    // 回填参数为空,则默认回填第一个间隔的序列号.
    dataSeq.backfill(5);



    // 6，打包，获得产品包
    // 添加俩个参数  序列号  数据内容
    DataProduction dataProduction = dataGenerator.build();

    // 7，获得打包后的包

    PackageForm datas2 = this.datasForm();
    DataPackage packaged = dataProduction.packaging(datas2);
    System.out.println("result=="+packaged.getZipUrl());
    // 获取url
  }





  private UserDemo datas2(){
    // 模拟数据
    UserDemo userDemo = new UserDemo();
    userDemo.setName("aaa");
    return userDemo;
  }


  private DataForm datas3(){
    // 模拟数据
    DataForm dataForm = new DataForm();
    dataForm.setData_type_code("ccc");
    dataForm.setValue("vvvvvv");
    return dataForm;
  }


  private PackageBean datas() {
    // 模拟数据
    PackageBean tfPackage121 = new PackageBean();

    tfPackage121.setPackageName("autoPackageName");
    tfPackage121.setIsBroadcast("false");

    Receivers receivers1212 = new Receivers();
    receivers1212.setApplicationCode("DemoTopic");
    receivers1212.setPlatformCode("platCode");
    receivers1212.setProcessCode("addUser");

    tfPackage121.setReceivers(receivers1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }
  private PackageForm datasForm() {
    // 模拟数据
    PackageForm tfPackage121 = new PackageForm();

    tfPackage121.setPackageName("autoPackageName");
    tfPackage121.setIsBroadcast("false");

    Receivers receivers1212 = new Receivers();
    receivers1212.setApplicationCode("DemoTopic");
    receivers1212.setPlatformCode("platCode");
    receivers1212.setProcessCode("addUser");

    tfPackage121.setReceivers(receivers1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }

}