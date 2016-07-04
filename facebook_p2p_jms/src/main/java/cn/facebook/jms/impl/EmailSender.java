package cn.facebook.jms.impl;

import javax.jms.Message;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cn.facebook.jms.MessageSender;

@Component("emailSender")
public class EmailSender implements MessageSender {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.default.from}")
	private String from;
	
	@Override
	public void send(Message message) {
		System.out.println("发送邮件=============================================");
		// 1.创建一个MimeMessage
		/*
		MimeMessage mm = mailSender.createMimeMessage();

		// 3.设置相关属性
		try {
			// 2.得到一个设置邮件相关信息的对象
			MimeMessageHelper helper = new MimeMessageHelper(mm, true);
			helper.setFrom("lqingfang_312@sina.com"); // 从哪发送邮件
			helper.setTo(email); // 发送到哪
			helper.setSubject("P2P邮件激活"); // 标题
			helper.setText(message.get, true);// 内容
		}
		// 发送邮件
		mailSender.send(mm);
		*/
	}

}
