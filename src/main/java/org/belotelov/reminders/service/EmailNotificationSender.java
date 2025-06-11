package org.belotelov.reminders.service;

import org.belotelov.reminders.entity.NotificationSender;
import org.belotelov.reminders.entity.Reminder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationSender implements NotificationSender {
    
    private final JavaMailSender sender;
    private final String MAIL_FROM;
    

    public EmailNotificationSender(JavaMailSender sender, 
            @Value("${spring.mail.username}") String senderMail) {
        this.sender = sender;
        this.MAIL_FROM = senderMail;
    }
    
    @Override
    public void send(Reminder reminder) {
        
        String receiver = reminder.getUser().getEmail();
        
        SimpleMailMessage letter = new SimpleMailMessage();
        
        letter.setTo(receiver);
        letter.setFrom(MAIL_FROM);
        letter.setSubject(reminder.getTitle());
        letter.setText(reminder.getDescription());
        
        sender.send(letter);
    }
}
