package com.liyong.service.impl;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.liyong.service.IEmailUntilService;

/**
 * 发送邮件信息
 * @author LY
 *  http://www.ityouknow.com
 */
@Service
public class EmailUntilServiceImpl implements IEmailUntilService {

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//邮件的发送者
    @Value("${spring.mail.username}")
    private String from;
    
    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    
    //subject  主题
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }
    }
    
    //发送html格式邮件
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }
    
    // 发送带附件的邮件
    public void sendAttachmentsMail(String to, String subject, String content, String filePath){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            
            //添加多个附件可以使用多条 helper.addAttachment(fileName, file)
            helper.addAttachment(fileName, file);           

            mailSender.send(message);
            logger.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }
    
    /**
     * 发送带静态资源的邮件   邮件中的静态资源一般就是指图片
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
    

    
    /**
     * 发送模板邮件
     * @param title   邮件标题
     * @param toUserEmilAddr   发送对象
     * @param templateName 模板名称
     * @param paramsMap 模板参数
     * @param copyToUserAddr 抄送对象   可为null
    */
    public void sendMailTemplateMessge(String title,String toUserEmilAddr,
    		String templateName,Map<String,Object> paramsMap,String copyToUserAddr) {
      try {
    	    //定义一个Thymelf上下文
    	    Context context = new Context();
    	    for (Map.Entry<String, Object> entry : paramsMap.entrySet()) { 
    	      context.setVariable(entry.getKey(),entry.getValue());
    	    }
    	    
    	    //通过模版引擎将数据渲染到指定模板中
    	    String content = templateEngine.process(templateName, context);
    	    MimeMessage mimeMessage = mailSender.createMimeMessage();
    	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    	    mimeMessageHelper.setFrom(from);
    	    mimeMessageHelper.setTo(toUserEmilAddr); //接受人
    	    mimeMessageHelper.setSubject(title); //设置主题
    	    mimeMessageHelper.setText(content,true);  //发送内容
    	    if(copyToUserAddr!= null && copyToUserAddr !=""){
    	    	mimeMessageHelper.setCc(copyToUserAddr);//抄送对象
    	    }
    	    mailSender.send(mimeMessage);
    	    logger.info("模板邮件已经发送");
      } catch (Exception e) {
          logger.error("发送模板邮件时发生异常！", e);
      }
    }

}
