package com.liyong;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyong.common.vo.ValueObject;
import com.liyong.service.IEmailUntilService;
import com.liyong.suport.JmsUtil;
import com.liyong.suport.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	@Resource
	private IEmailUntilService emailUntilService;	
	
	public void testEmail(){
		String toUserEmilAddr = "768665210@qq.com";
		String title = "账户验证邮件";
		String templateName = "emialTemplate";
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("userId", "张三");
		paramsMap.put("yzm", Math.random()*1000);
		emailUntilService.sendMailTemplateMessge(title, toUserEmilAddr, templateName, paramsMap, null);
	}
	
	
	public void testJms(){
		System.out.println("发送推送消息。。。。start....");
		ValueObject objs = new ValueObject();
		objs.setJmsType("哈哈哈哈，这个是测试发送消息的。。。。");
		
		try {
			JmsUtil.pushToMsgSenderQueue(objs);
			JmsUtil.pushToEmployeeQueue(objs);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("发送推送消息。。。。end....");
	}
	
	
	public void testRedis(){
		RedisUtil.redisSaveObject("text1", "1-----------------111111");
		Object obj = RedisUtil.redisQueryObject("text1");
		System.out.println("-------" + obj.toString()); 
	}

}
