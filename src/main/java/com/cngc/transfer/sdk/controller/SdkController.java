package com.cngc.transfer.sdk.controller;

import com.cngc.transfer.sdk.aspect.DataProcessResult;
import com.cngc.transfer.sdk.aspect.TransformServices;
import com.cngc.transfer.sdk.aspect.UserDemo;
import com.cngc.transfer.sdk.common.DataGenerator;
import com.cngc.transfer.sdk.common.DataGeneratorContext;
import com.cngc.transfer.sdk.common.DataGeneratorContextHolder;
import com.cngc.transfer.sdk.common.DataPackage;
import com.cngc.transfer.sdk.common.DataProduct;
import com.cngc.transfer.sdk.form.GeneratorBean;
import com.cngc.transfer.sdk.form.PackageBean;
import com.cngc.transfer.sdk.form.PackageForm;
import com.cngc.transfer.sdk.form.Receiver;
import com.cngc.transfer.sdk.process.condition.AfterPackageProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SdkController {

  private DataPackage dataPackage = new DataPackage();

  @Autowired
  private DataGeneratorContext dataGeneratorContext;

  @GetMapping("/transformTest")
  public DataProcessResult transformTest() {
    TransformServices transformServices = new TransformServices();
    transformServices.process(null);
    return DataProcessResult.SUCCESS;
  }

  @GetMapping("/packageListByCode")
  public void packageListByCode() {
    List<PackageBean> plat = dataPackage.packageListByCode("plat");
    System.out.println(plat);
  }

  @GetMapping("/createGenerator")
  public void createGenerator() {
    GeneratorBean generatorBean = new GeneratorBean();
    generatorBean.setGeneratorCode("test9");
    generatorBean.setGeneratorType("1");
    generatorBean.setSequenceCode("343");
    GeneratorBean generator = this.dataGeneratorContext.createGenerator(generatorBean);
    System.out.println(generator);
  }

  @GetMapping("/packageByCode")
  public void packageByCode() {
    PackageBean generator = this.dataPackage.packageByCode("d6bd4c18-c260-4437-abd5-22c267a968cd");
    System.out.println(generator);
  }

  @GetMapping("/testDemo")
  public void testDemo() throws Exception{
    // 获取生成器上下文
    DataGeneratorContext context = DataGeneratorContextHolder.getContext();
    // 获取生成器
    DataGenerator dataGenerator = context.getGenerator("BASIC_001");
    // 模拟数据
    UserDemo datas = this.UserData();
//    InputStream inputStream = new FileInputStream("D:\\myroot\\json202008181034002.json");
    // 追加数据
//    dataGenerator.append(inputStream, "file");
    dataGenerator.append(datas);
    // 打包，获得产品包
    DataProduct dataProduct = dataGenerator.build();
    PackageForm packageForm = this.datasForm();
    // {"AfterPackageProcess": "1,2,3,4"}
    packageForm.setCondition(new AfterPackageProcess().addPackageId("1").addPackageId("2").getCondition());
    // 获得包裹信息
    DataPackage packaged = dataProduct.packaging(packageForm);
    System.out.println("result=="+packaged);
  }

  private UserDemo UserData(){
    // 模拟数据
    UserDemo userDemo = new UserDemo();
    userDemo.setName("aaa");
    return userDemo;
  }

  private PackageForm datasForm() {
    // 模拟数据
    PackageForm tfPackage = new PackageForm();
    tfPackage.setPackageName("autoPackageName");
    tfPackage.setIsBroadcast(false);
    List<Receiver> receivers = new ArrayList<>();
    Receiver receiver = new Receiver();
    receiver.setApplicationCode("ORDER_APP_CODE");
    receiver.setPlatformCode("ORDER_PLATFORM_CODE");
    receiver.setProcessCode("addUser");
    receiver.setIgnore(true);
    receivers.add(receiver);
    Receiver receiverIGnoreTest = new Receiver();
    receiverIGnoreTest.setApplicationCode("BASIC_APP_CODE");
    receiverIGnoreTest.setPlatformCode("BASIC_PLATFORM_CODE");
    receiverIGnoreTest.setProcessCode("addUser");
    receiverIGnoreTest.setIgnore(true);
    receivers.add(receiverIGnoreTest);
    tfPackage.setReceivers(receivers);
    tfPackage.setBroadcastReceiver(null);
    return tfPackage;
  }
}
