package com.cngc.transfer.test;


import com.cngc.transfer.sdk.aspect.UserDemo;
import com.cngc.transfer.sdk.form.DataForm;
import com.cngc.transfer.sdk.form.PackageBean;
import com.cngc.transfer.sdk.form.PackageForm;
import com.cngc.transfer.sdk.form.Receiver;
import com.cngc.transfer.sdk.common.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductPackageTest.class)
public class ProductPackageTest {


  @Test
  public void testSdk(){
    DataGeneratorContext context = DataGeneratorContextHolder.getContext();
    DataGenerator dataGenerator = context.getGenerator("GE00001");

    // 模拟数据
    UserDemo datas = this.datas2();
//    DataForm datas = this.datas3();
    dataGenerator.append(datas);

    DataSequence dataSequence = dataGenerator.getDataSequence();


    dataSequence.skip();
    // 回填参数为空,则默认回填第一个间隔的序列号.
    dataSequence.backfill(5);



    // 6，打包，获得产品包
    // 添加俩个参数  序列号  数据内容
    DataProduction dataProduction = dataGenerator.build();

    // 7，获得打包后的包

    PackageForm packageForm = this.datasForm();
    DataPackage packaged = dataProduction.packaging(packageForm);
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

    Receiver receiver1212 = new Receiver();
    receiver1212.setApplicationCode("DemoTopic");
    receiver1212.setPlatformCode("platCode");
    receiver1212.setProcessCode("addUser");

    tfPackage121.setReceiver(receiver1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }
  private PackageForm datasForm() {
    // 模拟数据
    PackageForm tfPackage121 = new PackageForm();

    tfPackage121.setPackageName("autoPackageName");
    tfPackage121.setIsBroadcast("false");

    Receiver receiver1212 = new Receiver();
    receiver1212.setApplicationCode("DemoTopic");
    receiver1212.setPlatformCode("platCode");
    receiver1212.setProcessCode("addUser");

    tfPackage121.setReceivers(receiver1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }
  private PackageForm datasForm2() {
    // 模拟数据
    PackageForm tfPackage121 = new PackageForm();

    tfPackage121.setPackageName("autoPackageName222");
    tfPackage121.setIsBroadcast("false");

    Receiver receiver1212 = new Receiver();
    receiver1212.setApplicationCode("DemoTopic");
    receiver1212.setPlatformCode("platCode");
    receiver1212.setProcessCode("addUser");

    tfPackage121.setReceivers(receiver1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }


}