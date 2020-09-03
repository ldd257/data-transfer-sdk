package com.cngc.transfer.sdk.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.constants.TfConstants;
import com.cngc.transfer.sdk.form.PackageForm;
import com.cngc.transfer.sdk.form.Receiver;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
public class DataProduct {

  private MyRequestUtils myRequestUtils = new MyRequestUtils();

  private String id;

  private String generatorCode;
  private static final String PACKAGEURL = "/transfer/packages/";
  /**
   * 数据打包.
   */
  public DataPackage packaging(PackageForm packageBean) {
    JSONObject beanJson = new JSONObject();
    JSONObject broadcastReceiverJson = new JSONObject();
    List<JSONObject> receiverJsonList = new ArrayList<>();

    if (Boolean.TRUE.equals(packageBean.getIsBroadcast())) {

      broadcastReceiverJson.put(TfConstants.ProcessConstants.APPLICATION_CODE
          ,packageBean.getBroadcastReceiver().getApplicationCode());
      broadcastReceiverJson.put(TfConstants.ProcessConstants.PROCESS_CODE
          ,packageBean.getBroadcastReceiver().getProcessCode());
      broadcastReceiverJson.put(TfConstants.ProcessConstants.IGNORE
          ,packageBean.getBroadcastReceiver().getIgnore());
    } else {
      if (!packageBean.getReceivers().isEmpty()) {
        for(Receiver receiver : packageBean.getReceivers()) {
          JSONObject receiverJson = new JSONObject();
          receiverJson.put(TfConstants.ProcessConstants.PLATFORM_CODE,receiver.getPlatformCode());
          receiverJson.put(TfConstants.ProcessConstants.APPLICATION_CODE,receiver.getApplicationCode());
          receiverJson.put(TfConstants.ProcessConstants.PROCESS_CODE,receiver.getProcessCode());
          receiverJson.put(TfConstants.ProcessConstants.IGNORE,receiver.getIgnore());
          receiverJsonList.add(receiverJson);
        }
      } else {
        JSONObject receiverJson = new JSONObject();
        receiverJson.put(TfConstants.ProcessConstants.PLATFORM_CODE,"");
        receiverJson.put(TfConstants.ProcessConstants.APPLICATION_CODE,"");
        receiverJson.put(TfConstants.ProcessConstants.PROCESS_CODE,"");
        receiverJson.put(TfConstants.ProcessConstants.IGNORE,false);
        receiverJsonList.add(receiverJson);
      }
    }
    beanJson.put(TfConstants.ProcessConstants.IS_BROADCAST,packageBean.getIsBroadcast());
    beanJson.put(TfConstants.ProcessConstants.RECEIVERS,receiverJsonList);
    beanJson.put(TfConstants.ProcessConstants.BROADCAST_RECEIVER,broadcastReceiverJson);
    beanJson.put(TfConstants.ProcessConstants.PACKAGE_NAME,packageBean.getPackageName());
    beanJson.put(TfConstants.ProcessConstants.CONDITION,JSONUtil.toJsonStr(packageBean.getCondition()));
    beanJson.put(TfConstants.ProcessConstants.PRODUCT_ID,id);
    String result = myRequestUtils.myRequestPost(PACKAGEURL+ generatorCode +"/packing", beanJson);
    DataPackage dataPackage = "".equals(result) ? new DataPackage() : JSONUtil.toBean(result, DataPackage.class);
    dataPackage.setMyRequestUtils(myRequestUtils);
    // 保存数据生成
    return dataPackage;
  }

}
