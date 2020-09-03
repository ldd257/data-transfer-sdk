package com.cngc.transfer.sdk.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @Author sunzhiyuan
 * @Version  1.0
 * @Return
 * @Date 2020/8/31 9:44
 */
@DataProcessor("addUser")
public class TransformServices {
    public static final Logger logger = LoggerFactory.getLogger(TransformServices.class);

    @DataRun
    public DataProcessResult process(List<UserDemo> users) {
        // 自己的业务实现
        return DataProcessResult.SUCCESS;
    }
}
