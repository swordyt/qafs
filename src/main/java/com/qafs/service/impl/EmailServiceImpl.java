package com.qafs.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.qafs.dao.EmailRecordMapper;
import com.qafs.domain.EmailRecord;
import com.qafs.service.IEmailSendService;

@Service
public class EmailServiceImpl implements IEmailSendService {
	private static final Logger log = Logger.getLogger(EmailServiceImpl.class);
	@Value("${spring.email.account}")
	private String ACCOUNT;
	@Value("${spring.email.password}")
	private String PASSWORD;
	@Value("${spring.email.smtphost}")
	private String SMTP;
	@Value("${spring.email.protocol}")
	private String PROTOCOL;
	@Resource
	EmailRecordMapper emailRecordDao;
	private Properties props;

	Session session;

	@Override
	public Boolean send(EmailRecord email) {
		init();
		Transport transport = null;
		try {
			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(email);
			// 4. 根据 Session 获取邮件传输对象
			transport = session.getTransport();
			// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
			transport.connect(ACCOUNT, PASSWORD);
			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
			// 获取到的是在创建邮件对象时添加的所有收件人,
			// 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			log.info("【x】邮件创建失败：" + JSON.toJSONString(email));
			return false;
		} finally {
			// 7. 关闭连接
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				log.error("邮件发送transport关闭出现异常");
			}
		}
		return true;

	}

	private MimeMessage createMimeMessage(EmailRecord record) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
		message.setFrom(new InternetAddress(ACCOUNT, "Admin", "UTF-8"));
		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				record.getReceiver(), record.getReceiver(), "UTF-8"));
		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject(record.getSubject(), "UTF-8");
		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
		message.setContent(record.getText(), "text/html;charset=UTF-8");
		// 6. 设置发件时间
		message.setSentDate(new Date());
		// 7. 保存设置
		message.saveChanges();
		return message;
	}

	private void init() {
		if (props == null) {
			props = new Properties(); // 参数配置
			props.setProperty("mail.transport.protocol", PROTOCOL); // 使用的协议（JavaMail规范要求）
			props.setProperty("mail.smtp.host", SMTP); // 发件人的邮箱的 SMTP // 服务器地址
			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
		}
		if (session == null) {
			session = Session.getInstance(props);
			session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		}

	}

	@Override
	public Boolean send(Integer id) {
		EmailRecord emailRecord = emailRecordDao.selectByPrimaryKey(id);
		if (emailRecord == null) {
			return false;
		}
		return send(emailRecord);
	}
}
