package com.liyong.common.init;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.liyong.suport.JmsUtil;
import com.liyong.suport.MongoUtil;
import com.liyong.suport.RedisUtil;

/**
 * 系统初始化节点
 * @author liyong
 */
@Service()
public class SystemInitialPoint implements InitializingBean{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	//activeMQ 消息队列
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination messageSenderDestination;
    @Autowired
    private Destination employeeDestination;
    
    //大文件存储
    @Autowired
    private MongoTemplate mongoTemplate;
    
    //redis 消息缓存
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplateNoSer;
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
       logger.info("初始化JMS服务。。。");
       JmsUtil.initJmsTemplate(jmsTemplate,messageSenderDestination,employeeDestination);
       
       logger.info("初始化mongoTemplate服务。。。");
       MongoUtil.initMongoTemplate(mongoTemplate);
       
       logger.info("初始化Redis服务。。。");
       RedisUtil.initRedisTemplate(redisTemplate);
       RedisUtil.initRedisTemplateNoSer(redisTemplateNoSer);
        
    }
    
    

}
