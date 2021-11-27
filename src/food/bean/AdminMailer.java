package food.bean;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailer")
public class AdminMailer {

	@Autowired
	JavaMailSender mailer;

	public void send(String from, String[] to, String subject, String body) {
		try {
			MimeMessage mail = mailer.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);

			helper.setFrom(from, from);
			helper.setTo(to);

			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);

			mailer.send(mail);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void sendmailer(String from, String to, String subject, String body) {
		try {
			MimeMessage mail = mailer.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mail);

			helper.setFrom(from, from);
			helper.setTo(to);

			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);

			mailer.send(mail);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
