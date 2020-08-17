package com.datatransfer.common;

import cn.hutool.http.HttpUtil;
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
@Component
public class DataSeq {

    /**
     * 自增序列seq
     * 当前序列
     */
    private String seq;

    private int flag;


    public DataSeq (){
        super();
//        this.seq = this.selectSeq();
    }

    /**
     * 跳过
     */
    public void skip(){
        flag = 1;

    }

    /**
     * 回填
     */
    public void backFill(){
        flag = 2;
    }

    /**
     * 查询当前序列
     */
    public String selectSeq(){
        // 查询seq  TODO
        return HttpUtil.get("http://localhost:8080/transfer/generators/code" );

    }
}
