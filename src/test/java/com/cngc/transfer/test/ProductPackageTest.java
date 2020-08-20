package com.cngc.transfer.test;


import com.cngc.transfer.sdk.aspect.UserDemo;
import com.cngc.transfer.sdk.common.DataGenerator;
import com.cngc.transfer.sdk.common.DataGeneratorContext;
import com.cngc.transfer.sdk.common.DataGeneratorContextHolder;
import com.cngc.transfer.sdk.common.DataPackage;
import com.cngc.transfer.sdk.common.DataProduct;
import com.cngc.transfer.sdk.common.DataSequence;
import com.cngc.transfer.sdk.form.DataForm;
import com.cngc.transfer.sdk.form.PackageBean;
import com.cngc.transfer.sdk.form.PackageForm;
import com.cngc.transfer.sdk.form.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    DataProduct dataProduction = dataGenerator.build();

    // 7，获得打包后的包

    PackageForm packageForm = this.datasForm();
    DataPackage packaged = dataProduction.packaging(packageForm);
    System.out.println("result=="+packaged.getZipUrl());
    // 获取url
  }


  public void xxTest(){
    // 从数据生成器上下文持有者中获取数据生成器上下文
    DataGeneratorContext context = DataGeneratorContextHolder.getContext();
// 使用数据生成器上下文获取数据生成器
    DataGenerator dataGenerator = context.getGenerator("code");
    UserDemo datas = this.datas2();
    dataGenerator.append(datas);
// 数据序列
    DataSequence dataSeq = dataGenerator.getSequence();
    dataSeq.skip();
// 回填参数为空,则默认回填第一个间隔的序列号.
    dataSeq.backfill(5);

// 数据产品
    DataProduct dataProduct = dataGenerator.build();

// 数据包裹
    PackageForm packageForm = this.datasForm();
    DataPackage dataPackage = dataProduct.packaging(packageForm);
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
    tfPackage121.setIsBroadcast(false);

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
    tfPackage121.setIsBroadcast(false);

    Receiver receiver1212 = new Receiver();
    receiver1212.setApplicationCode("DemoTopic");
    receiver1212.setPlatformCode("platCode");
    receiver1212.setProcessCode("addUser");

    tfPackage121.setReceivers(receiver1212);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }


}
