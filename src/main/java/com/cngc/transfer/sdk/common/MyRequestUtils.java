package com.cngc.transfer.sdk.common;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

/**
 * 封装get、post请求
 */
public class MyRequestUtils {

  /**
   * get封装
   * @param url url请求地址
   * @return
   */
  public String myRequestGet(String url) {
    return HttpUtil.get(TransferConfig.TRANSFER_PLATFORM + url);
  }

  /**
   * post封装
   * @param url url 请求地址
   * @param object 请求实体body
   * @return
   */
  public String myRequestPost(String url,Object object) {
    return HttpUtil.post(TransferConfig.TRANSFER_PLATFORM + url, JSONUtil.toJsonStr(object));
  }

  /**
   * put封装
   * @param url url 请求地址
   * @param object 请求实体body
   * @return
   */
  public String myRequestPut(String url,Object object) {
    return HttpRequest.put(TransferConfig.TRANSFER_PLATFORM +url)
        .header("Content-Type", "application/json")
        .body(JSONUtil.toJsonStr(JSONUtil.toJsonStr(object)))
        .execute().body();
  }

  /**
   * delete封装
   * @param url url 请求地址
   * @return
   */
  public String myRequestDelete(String url) {
    return HttpRequest.delete(TransferConfig.TRANSFER_PLATFORM +url)
        .header("Content-Type", "application/json")
        .execute().body();
  }
}
