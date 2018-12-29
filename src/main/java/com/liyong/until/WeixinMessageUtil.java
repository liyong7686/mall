package com.liyong.until;


import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
 
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.liyong.until.weixin.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


public class WeixinMessageUtil {

	
	/**
	 * 请求消息类型：文本
	 */
	public static String REQ_MESSAGE_TYPE_TEXT = "text";
 
	/**
	 * 请求消息类型：图片
	 */
	public  static String REQ_MESSAGE_TYPE_IMAGE="image";
 
	/**
	 * 请求消息类型：语音
	 */
	public static String REQ_MESSAGE_TYPE_VOICE="voice";
	
	/**
	 * 请求消息类型：视频
	 */
	public static String REQ_MESSAGE_TYPE_VIDEO="video";
	
	/**
	 * 请求消息类型：链接
	 */
	public static String REQ_MESSAGE_TYPE_LINK = "link";
	
	/**
	 * 请求消息类型：地理位置
	 */
	public  static String REQ_MESSAGE_TYPE_LOCATION="location";
	
	/**
	 * 请求消息类型：小视频
	 */
	public static String REQ_MESSAGE_TYPE_SHORTVIDEO="shortvideo";
	
	/**
	 *请求消息类型：事件推送 
	 */
	public static String REQ_MESSAGE_TYPE_EVENT = "event";
	
	/**
	 * 返回消息类型：文本
	 */
	public static String RESP_MESSAGE_TYPE_TEXT = "text";
	
	/**
	 * 消息返回类型：图片
	 */
	public static String RESP_MESSAGE_TYPE_IMAGE="image";
	
	/**
	 * 消息返回类型:语音
	 */
	public static String RESP_MESSAGE_TYPE_VOICE = "voice";
	
	/**
	 * 消息返回类型：音乐
	 */
	public static String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	/**
	 * 消息返回类型：图文
	 */
	public static  String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 消息返回类型：视频
	 */
	public static String RESP_MESSAGE_TYPE_VIDEO="video";
	
	/**
	 * 事件类型:订阅
	 */
	public static String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * 事件类型：取消订阅
	 */
	public static String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	/**
	 * 事件类型：scan(关注用户扫描带参二维码)
     */
	public static String EVENT_TYPE_SCAN = "scan";
 
	/**
	 * 事件类型：location(上报地理位置)
	 */
	public static String EVENT_TYPE_LOCATION = "location";
	
	/**
	 * 事件类型：CLICK(点击菜单拉取消息)
	 */
	public static String EVENT_TYPE_CLICK ="CLICK";
	
	/**
	 * 事件类型：VIEW(自定义菜单URl视图)
	 */
	public static String EVENT_TYPE_VIEW ="VIEW";
	
	/**
	 * 事件类型：TEMPLATESENDJOBFINISH(模板消息送达情况提醒)
	 */
	public static String EVENT_TYPE_TEMPLATESENDJOBFINISH="TEMPLATESENDJOBFINISH";
	
	
	/**
	  * @Description: 解析微信服务器发过来的xml格式的消息将其转换为map
	  * @Parameters: WeixinMessageUtil
	  * @Return: Map<String, String>
	  * @Create Date: 2017年10月11日上午11:41:23
	  * @Version: V1.00 
	  * @author:来日可期
	  */
	public static Map<String, String> parseXml(HttpServletRequest request)throws Exception{
		
		// 将解析结果存储在HashMap中
		Map<String, String>map =new HashMap<String, String>();
		// 从request中得到输入流
		InputStream  inputStream=request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到XML的根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		// 判断又没有子元素列表
		if (elementList.size()==0){
			map.put(root.getName(), root.getText());
		}else {
			for (Element e : elementList)
				map.put(e.getName(), e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		System.out.println("---------xml转换为map-----:"+map);
		return map;
	}
	
	/**
     * 转换文本消息
     *
     * @param textMessage
     *          
     * @return xml
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }
 
    /**
     *
     * 定义xstream让value自动加上CDATA标签
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                boolean cdata = false;
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
 
                    if (!name.equals("xml")) {
                        char[] arr = name.toCharArray();
                        if (arr[0] >= 'a' && arr[0] <= 'z') {
                            // arr[0] -= 'a' - 'A';
                            arr[0] = (char) ((int) arr[0] - 32);
                        }
                        name = new String(arr);
                    }
 
                    super.startNode(name, clazz);
 
                }
 
                @Override
                public void setValue(String text) {
                    if (text != null && !"".equals(text)) {
                        Pattern patternInt = Pattern .compile("[0-9]*(\\.?)[0-9]*");
                        Pattern patternFloat = Pattern.compile("[0-9]+");
                        if (patternInt.matcher(text).matches()  || patternFloat.matcher(text).matches()) {
                            cdata = false;
                        } else {
                            cdata = true;
                        }
                    }
                    super.setValue(text);
                }
 
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
 
    });
	

}
