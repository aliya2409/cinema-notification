package com.epam.notification.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {
    public static final String TICKET_SENT_SUBJECT = "Your tickets are booked!";
    public static final String TICKET_SENT_MESSAGE = "The tickets are attached to this letter.";
    public static final String TICKET_FILE = "static/ticket.png";
    private final JavaMailSender emailSender;

    public void sendTicket(String to) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(TICKET_SENT_SUBJECT);
            helper.setText(TICKET_SENT_MESSAGE);
            FileSystemResource file
                    = new FileSystemResource(Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource(TICKET_FILE)).toURI()).toFile());
            helper.addAttachment("ticket.png", file);
            emailSender.send(message);
        } catch (MessagingException | URISyntaxException e) {
            log.error(e.getMessage());
        }

    }
}
