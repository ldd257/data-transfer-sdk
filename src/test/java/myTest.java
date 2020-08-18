import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.common.TransferConfig;
import com.cngc.transfer.sdk.entity.TfProductEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductPackageTest.class)
public class myTest {

 @Autowired
 private TransferConfig transferConfig;

  @Test
  public void httpGetTest(){
    System.out.println(transferConfig);
  }
}
