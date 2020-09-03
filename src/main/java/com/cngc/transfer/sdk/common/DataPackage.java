package com.cngc.transfer.sdk.common;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cngc.transfer.sdk.form.PackageBean;
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
public class DataPackage {

  /**
   * 包裹id.
   */
  private String id;
  /**
   * 包裹code.
   */
  private String code;
  /**
   * 包裹code.
   */
  private String fileUrl;
  /**
   * 创建时间.
   */
  private String createTime;

  private MyRequestUtils myRequestUtils = new MyRequestUtils();

  /**
   * 通过平台编码查询数据包裹列表.
   * @param platformCode 平台编码.
   * @return 返回package列表
   */
  public List<PackageBean> packageListByCode(String platformCode) {
    String result = myRequestUtils.myRequestGet("/transfer/packages/"+ platformCode +"/byCode");
    JSONArray jr = JSONUtil.parseArray(result);
    List<PackageBean> packageBeanList = new ArrayList<>();
    for (Object object : jr){
      JSONObject jsonObject = JSONUtil.parseObj(object);
      PackageBean packageBean = jsonObject.toBean(PackageBean.class);
      packageBeanList.add(packageBean);
    }
    return packageBeanList;
  }

  /**
   * 通过包裹编码查询数据.
   * @param packageCode 包裹编码.
   * @return
   */
  public PackageBean packageByCode(String packageCode) {
    String result = myRequestUtils.myRequestGet("/transfer/packages/"+ packageCode +"/package");
    JSONObject jsonObject = JSONUtil.parseObj(result);
    return jsonObject.toBean(PackageBean.class);
  }
}
