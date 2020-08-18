package com.datatransfer.common;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

/**
 * 封装get、post请求
 */
@Data
@Component
public class MyRequestUtils {

  public   String TRANFER_PLATFORM_URL  ;
  public  String TRANFER_PLATFORM_PORT  ;
//  private  String TRANFER_PLATFORM_URL = "http://localhost";
//  private  String TRANFER_PLATFORM_PORT = "8080";
  /**
   * get封装
   * @param url url请求地址
   * @return
   */
  public  String myRequestGet(String url){

    return HttpUtil.get(TRANFER_PLATFORM_URL +":"+ TRANFER_PLATFORM_PORT + url);
  }

  /**
   * post封装
   * @param url url 请求地址
   * @param object 请求实体body
   * @return
   */
  public   String myRequestPost(String url,Object object){

    return HttpUtil.post(TRANFER_PLATFORM_URL +":"+ TRANFER_PLATFORM_PORT + url, JSONUtil.toJsonStr(object));
  }

  /**
   * put封装
   * @param url url 请求地址
   * @param object 请求实体body
   * @return
   */
  public   String myRequestPut(String url,Object object) {
    String result = HttpRequest.put(TRANFER_PLATFORM_URL +":"+ TRANFER_PLATFORM_PORT +url)
        .header("Content-Type", "application/json")
        .body(JSONUtil.toJsonStr(JSONUtil.toJsonStr(object)))
        .execute().body();
    return result;
  }
    /**
     * delete封装
     * @param url url 请求地址
     * @return
     */
    public   String myRequestDelete(String url){
    String result = HttpRequest.delete(TRANFER_PLATFORM_URL +":"+ TRANFER_PLATFORM_PORT +url)
        .header("Content-Type", "application/json")
        .execute().body();
    return result;
  }

}
