package com.msit.jatin.main.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.msit.jatin.main.exceptions.BugTrackingException;
import com.msit.jatin.main.model.NotificationEmail;

@Service
public class MailService {
	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;

	public MailService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
		this.mailSender = mailSender;
		this.mailContentBuilder = mailContentBuilder;
	}

	@Async
	void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("bugtracker@email.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		try {
			mailSender.send(messagePreparator);

		} catch (MailException e) {

			throw new BugTrackingException(
					"Exception occurred when sending mail to " + notificationEmail.getRecipient());
		}
	}

}
