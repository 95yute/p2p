package cn.facebook.service.email.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.facebook.service.email.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	// 发送邮件操作
	// 参数 email 要发送的邮箱地址
	// 参数 content 邮件内容
	@Override
	public void sendEmail(String email, String content) {
		// 1.创建一个MimeMessage
				MimeMessage mm = mailSender.createMimeMessage();

				// 3.设置相关属性
				try {
					// 2.得到一个设置邮件相关信息的对象
					MimeMessageHelper helper = new MimeMessageHelper(mm, true);
					helper.setFrom("lqingfang_312@sina.com"); // 从哪发送邮件
					helper.setTo(email); // 发送到哪
					helper.setSubject("P2P邮件激活"); // 标题
					helper.setText(content, true);// 内容
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				// 发送邮件
				mailSender.send(mm);
	}

}
