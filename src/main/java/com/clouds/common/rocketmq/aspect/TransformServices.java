package com.clouds.common.rocketmq.aspect;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DataProcessor("addUser")
public class TransformServices {

    @DataRun(UserDemo.class)
    public DataProcessResult process(List<UserDemo> users) {
        // 自己的业务实现
        System.out.println("跳出切面");
        System.out.println(users);
        return DataProcessResult.SUCCESS;
    }
}
