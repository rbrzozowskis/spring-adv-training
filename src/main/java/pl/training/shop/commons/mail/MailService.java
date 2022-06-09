package pl.training.shop.commons.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@RequiredArgsConstructor
public class MailService {

    private static final String ENCODING = "utf-8";

    private final JmsTemplate jmsTemplate;
    private final Queue mailQueue;
    private final JavaMailSender mailSender;

    public void send(MailMessage message) {
        jmsTemplate.send(mailQueue, session -> session.createObjectMessage(message));
    }

    @JmsListener(destination = "Mail", containerFactory = "mailQueueContainerFactory")
    void onMailMessage(MailMessage message) {
        mailSender.send(mimeMessagePreparator(message));
    }

    private MimeMessagePreparator mimeMessagePreparator(MailMessage message) {
        return mimeMessage -> {
            var mailMessage = new MimeMessageHelper(mimeMessage, ENCODING);
            mailMessage.setFrom(message.getSender());
            mailMessage.setTo(message.getRecipients().toArray(new String[0]));
            mailMessage.setSubject(message.getTitle());
            mailMessage.setText(message.getText(), true);
        };
    }

}