import cn.hutool.json.JSONUtil;
import com.datatransfer.common.*;
import com.datatransfer.consumer.MQConsumerConfiguration;
import com.datatransfer.form.PackageBean;
import com.datatransfer.form.Receivers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductPackageTest.class)
public class ProductPackageTest {


  private MyRequestUtils myRequestUtils = new MyRequestUtils();
  /**
   * 列表
   */
  @Test
  public void productServer(){

      String serverUrl = "http://39.105.97.133";
      String serverPort = "8098";
//  private  String serverUrl = "http://localhost";
//  private  String serverPort = "8080";
    // 1,生成器
    DataGeneratorContext generator = new DataGeneratorContext(serverUrl, serverPort);
    generator.getInstance("GE00001");

    // 模拟数据
    PackageBean datas = this.datas();
    String json = JSONUtil.toJsonStr(datas);
    // 2，追加数据
    generator.appendJson(json);

    // 3，获得序列  查询序列
    DataSeq dataSeq = generator.getDataSeq();
//    String seqNum = dataSeq.getSeq();

    // 4，序列跳过
    dataSeq.skip();
    // 获取间隔序列号列表 [17]
    // 5，序列回填
    dataSeq.backFill();



    // 6，打包，获得产品包
    // 添加俩个参数  序列号  数据内容
    DataProduction dataProduction = generator.build();

    // 7，获得打包后的包
    DataPackage packaged = dataProduction.packaging(datas);
    System.out.println("result=="+packaged.getZipUrl());
    // 获取url
  }





  private PackageBean datas(){
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


  @Test
  public void xxxTest(){
    PackageBean datas = this.datas();
    String json = JSONUtil.toJsonStr(datas);
    String fileUrl = myRequestUtils.myRequestPost("/transfer/oss/upload", json);
    System.out.println(fileUrl);
  }




}
