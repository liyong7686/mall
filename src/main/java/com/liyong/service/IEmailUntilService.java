package com.liyong.service;

import java.util.Map;

public interface IEmailUntilService {

	public void sendSimpleMail(String to, String subject, String content);
	
	public void sendHtmlMail(String to, String subject, String content) ;
	
	public void sendAttachmentsMail(String to, String subject, String content, String filePath);
	
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

	public void sendMailTemplateMessge(String title,String toUserEmilAddr,
    		String templateName,Map<String,Object> paramsMap,String copyToUserAddr);
}
