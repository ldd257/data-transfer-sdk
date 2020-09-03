package com.cngc.transfer.test;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cngc.transfer.sdk.aspect.DataProcessor;
import com.cngc.transfer.sdk.aspect.DataRun;
import com.cngc.transfer.sdk.common.MyRequestUtils;
import com.cngc.transfer.sdk.common.TransferConfig;
import com.cngc.transfer.sdk.context.DataApplicationContextAware;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultProductTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultProductTest.class);



	private TransferConfig transferConfig;


	private MyRequestUtils myRequestUtils = new MyRequestUtils();

	@Test
	public void test() {
		myRequestUtils.myRequestGet("123123123123");
		System.out.println(transferConfig.TRANSFER_PLATFORM);
	}

	@Test
	public void antoMqTest() {

		String tags = "addUser";
		JSONArray parameter = new JSONArray();
		parameter.put(new JSONObject().put("name", "xiaoming"));
		Map<String, Object> map = DataApplicationContextAware.getApplicationContext();
		Class<? extends Object> clazz = null;
		for(Map.Entry<String, Object> entry : map.entrySet()){
			clazz = entry.getValue().getClass();//获取到实例对象的class信息

			// 获取 "类" 上的注解
			DataProcessor getAnnotation = clazz.getAnnotation(DataProcessor.class);
			logger.info("注解参数："+getAnnotation.value());

			if(tags.equals(getAnnotation.value())){
				Method[] method = clazz.getMethods();
				for(Method m:method){
					DataRun dataRun = m.getAnnotation(DataRun.class);
					if(null!=dataRun){
						try {
							Type[] parameterTypes = m.getGenericParameterTypes();
							Console.log(parameterTypes[0]);
							ParameterizedType t = (ParameterizedType)parameterTypes[0];
							Console.log(t.getClass());
							Console.log(t.getActualTypeArguments()[0]);// 可以得到参数化类型的参数实例

							//TODO 反序列化  取List<UserDemo>
//							Object obj = m.invoke(entry.getValue(), parameter.toList());

//							Console.log("处理结果：{}",obj);
							//TODO 根据返回值发送MQ至传输平台Server端
							//TODO sendToServer
//							SendResult sendResult = sendToServer(obj);
//							logger.info("发送处理结果："+sendResult + " 至Server端");

						} catch (Exception e){

						}
					}
				}
			}
		}


	}


}
