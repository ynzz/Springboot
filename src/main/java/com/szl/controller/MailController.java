package com.szl.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szl.vo.ResultVo;

/**
 * @author sunzl
 * @date 2018年11月25日
 * 
 * 发送邮件
 * 
 */
@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 发送简单邮件
	 * @return
	 */
	@RequestMapping("/sendMail")
	public ResultVo sendMail(){
		ResultVo resultVo = new ResultVo();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ynzz1234567@163.com");
		message.setTo("sunnyzl@163.com");
		message.setSubject("主题");
		message.setText("发送的内容");
		javaMailSender.send(message);
		resultVo.setCode("0");
		resultVo.setMsg("成功");
		return resultVo;
	}
	
	/**
	 * 发送带有附件的邮件
	 * @return
	 * @throws MessagingException 
	 */
	@RequestMapping("/sendMailAttachment")
	public ResultVo sendMailAttachment() throws MessagingException{
		ResultVo resultVo = new ResultVo();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("ynzz123**@163.com");
		helper.setTo("sunny***@163.com");
		helper.setSubject("主题");
		helper.setText("发送的内容");
		// src/main/resource下面的文件
		ClassPathResource resource = new ClassPathResource("/application.yml");
		helper.addAttachment("我是附件", resource);
		javaMailSender.send(mimeMessage);
		resultVo.setCode("0");
		resultVo.setMsg("成功");
		return resultVo;
	}
}
