package com.liyong.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.liyong.service.IEmailUntilService;
import com.liyong.service.IWeixinCoreService;
import com.liyong.suport.RedisUtil;
import com.liyong.until.WeixinMessageUtil;
import com.liyong.until.weixin.TextMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeixinCoreServiceImpl implements IWeixinCoreService {
	
    
    @Value("${wechat.appid}")
    private String appid; //微信号id
    
    @Value("${wechat.secret}")
    private String secret; //微信scret
    
	@Resource
	private IEmailUntilService emailUntilService;
	
	public String processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("------------微信消息开始处理-------------");
		// 返回给微信服务器的消息,默认为null
		String respMessage = null;
		try {
			
			// 默认返回的文本消息内容  
			String respContent = null;  
			// xml分析
			// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> map = WeixinMessageUtil.parseXml(request);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			
			System.out.println("toUserName----->"+toUserName);
			System.out.println("fromUserName--->"+fromUserName);
			System.out.println("msgType--->"+msgType);

			log.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);
			
			// 分析用户发送的消息类型，并作出相应的处理
			
			// 文本消息
			if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				 // 文本消息内容  
			    String content = map.get("Content");
				respContent = "发送的信息是：" + content ;
			}
			
			// 图片消息
			else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			
			// 语音消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            	respContent = "您发送的是语音消息！";

            }
			
			// 视频消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
            	respContent = "您发送的是视频消息！";

            }
			
			// 地理位置消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";

            }
			
			// 链接消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            	respContent = "您发送的是链接消息！";

            }
			
			// 事件推送(当用户主动点击菜单，或者扫面二维码等事件)
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            	
            	// 事件类型
            	String  eventType =map.get("Event");
            	System.out.println("eventType------>"+eventType);
            	// 关注
            	if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_SUBSCRIBE)){
      		        
            	}
            	// 取消关注
            	else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
            		
            	}
            	// 扫描带参数二维码
            	else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_SCAN)) {
            		
            	}
            	// 上报地理位置
            	else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_LOCATION)) {
            		
            	}
            	// 自定义菜单（点击菜单拉取消息）
            	else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_CLICK)) {
      		         
            		// 事件KEY值，与创建自定义菜单时指定的KEY值对应
            		String eventKey=map.get("EventKey");
            		System.out.println("eventKey------->"+eventKey);
            		
            	}
            	// 自定义菜单（(自定义菜单URl视图)）
            	else if (eventType.equals(WeixinMessageUtil.EVENT_TYPE_VIEW)) {
            		System.out.println("处理自定义菜单URI视图");
            	}            	
            }
			
			TextMessage textMessage=new TextMessage();
            textMessage.setToUserName(fromUserName);   //这里的ToUserName  是刚才接收xml中的FromUserName
            textMessage.setFromUserName(toUserName);   //这里的FromUserName 是刚才接收xml中的ToUserName  这里一定要注意，否则会出错
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setContent("稍等片刻，客服已在来的路上了，欢迎骚扰客服微信：13137617659");
            textMessage.setMsgType(msgType);
             
            String toUserEmilAddr = "872458906@qq.com";
			String title = "微信公众号消息通知邮件";
			String templateName = "emialTemplate";
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("userId", "客户发送的微信消息是:");
			paramsMap.put("yzm", map.get("Content"));
			emailUntilService.sendMailTemplateMessge(title, toUserEmilAddr, templateName, paramsMap, null); 
			
            respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
            
            
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("系统出错");
			System.err.println("系统出错");
			respMessage = null;
		} finally{
			if (null == respMessage) {
				
			}
		}
		return respMessage;
	}

	//每个公司一个微信号
	public String weixinToken(String companyId){
		String access_token = null;
		
		if(companyId == null){
			companyId = "companyId";
		}
		
        Object obj = RedisUtil.redisQueryObject("A_" + companyId + "_WeiXin_TOEKN");
        if(obj != null && obj.toString() != null && obj.toString() != "" ){
        	log.info("== redis 存在 ====");
        	access_token = obj.toString();
        }else{
        	log.info("== 重新获取 ====");
        	access_token = freshenWxToKen();
        }
        return access_token;
	}

	//刷新微信token
	public String freshenWxToKen(){
        String access_token = null;
        String grant_type = "client_credential";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+secret;  
        try {  
            URL urlGet = new URL(url);  
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();  
            http.setRequestMethod("GET"); // 必须是get方式请求  
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
            http.setDoOutput(true);  
            http.setDoInput(true);  
            http.connect();   
            InputStream is = http.getInputStream();
            int size = is.available();  
            byte[] jsonBytes = new byte[size];  
            is.read(jsonBytes);  
            String message = new String(jsonBytes, "UTF-8");  
            is.close();
            
            access_token = JSONObject.parseObject(message).getString("access_token");
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
        //写入缓存数据库和真实数据库中
        RedisUtil.redisSaveObject("A_companyId_WeiXin_TOEKN", access_token);
		
        return access_token;
	}
	
}
