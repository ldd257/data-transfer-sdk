package com.datatransfer.common;

import com.datatransfer.form.GeneratorForm;
import com.datatransfer.form.GeneratorForm2;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * 产品包裹服务
 *
 * @author ldd
 * @email ldd@gmail.com
 * @date 2020-08-11 13:37:16
 */
@Data
public class DataPackage {

  private GeneratorForm2 generatorForm;
  private Long productId;
  private String zipUrl;

  private  DataPackage(){}
  public DataPackage (GeneratorForm2 generatorForm, Long productId, String zipUrl){
    this.generatorForm = generatorForm;
    this.productId = productId;
    this.zipUrl = zipUrl;
  }


}
