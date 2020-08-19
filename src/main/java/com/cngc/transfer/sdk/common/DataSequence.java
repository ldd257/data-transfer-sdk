package com.cngc.transfer.sdk.common;

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
public class DataSequence {

    /**
     * 自增序列seq
     * 当前序列
     */
    private String seq;

    private int flag;
    private int flagInt;



    /**
     * 跳过
     */
    public void skip(){
        flag = 1;

    }

    /**
     * 回填
     */
    public void backfill(){
        flag = 2;
    }
    /**
     * 回填
     */
    public void backfill(int i){
        flag = 3;
        flagInt = i;
    }

    /**
     * 查询当前序列
     */
    public String selectSeq(){
        // 查询seq  TODO
        return HttpUtil.get("http://localhost:8080/transfer/generators/code" );

    }
}
