package com.liyong.suport;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import com.liyong.common.vo.ValueObject;

@Component
public class MessageHandler extends MessageListenerAdapter{

	@Override
    @JmsListener(destination = "messageSenderQueue")
    public void onMessage(Message message) {
		
        String messageBody = new String(message.toString());
        try {
			Thread.sleep(5000);
	    	System.out.println("监听消息：messageSenderQueue:" + messageBody);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       
    }

	@JmsListener(destination="employeeQueue")
	public void recieve1(ValueObject message) {
		System.out.println("###################" + message + "###################");

	}
	
}
