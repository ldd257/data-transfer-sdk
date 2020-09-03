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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductPackageTest.class)
public class ProductPackageTest {


  @Test
  public void testSdk()throws Exception{
    DataGeneratorContext context = DataGeneratorContextHolder.getContext();
    DataGenerator dataGenerator = context.getGenerator("GE00001");

    // 模拟数据
    UserDemo datas = this.datas2();
//    DataForm datas = this.datas3();
    InputStream inputStream = new FileInputStream("D:\\myroot\\json202008181034002.json");

    // 追加数据
    dataGenerator.append(inputStream, "json");

    DataSequence dataSequence = dataGenerator.getSequence();


    dataSequence.skip();
    // 回填参数为空,则默认回填第一个间隔的序列号.
    dataSequence.backfill(5);



    // 6，打包，获得产品包
    // 添加俩个参数  序列号  数据内容
    DataProduct dataProduction = dataGenerator.build();

    // 7，获得打包后的包

    PackageForm packageForm = this.datasForm();
    DataPackage packaged = dataProduction.packaging(packageForm);
    // 获取url
  }



  private UserDemo datas2(){
    // 模拟数据
    UserDemo userDemo = new UserDemo();
    userDemo.setName("aaa");
    return userDemo;
  }



  private PackageForm datasForm() {
    // 模拟数据
    PackageForm tfPackage121 = new PackageForm();

    tfPackage121.setPackageName("autoPackageName");
    tfPackage121.setIsBroadcast(false);

    List<Receiver> receivers = new ArrayList<>();
    Receiver receiver1 = new Receiver();
    receiver1.setApplicationCode("DemoTopic");
    receiver1.setPlatformCode("TEST_PLATFORM_CODE");
    receiver1.setProcessCode("addUser");

    Receiver receiver2 = new Receiver();
    receiver2.setApplicationCode("testCode");
    receiver2.setPlatformCode("TEST_PLATFORM_CODE");
    receiver2.setProcessCode("addUser");

    tfPackage121.setReceivers(receivers);
    tfPackage121.setBroadcastReceiver(null);

    return tfPackage121;
  }

}
