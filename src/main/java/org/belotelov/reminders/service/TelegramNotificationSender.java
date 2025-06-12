package org.belotelov.reminders.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.belotelov.reminders.entity.NotificationSender;
import org.belotelov.reminders.entity.Reminder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramNotificationSender implements NotificationSender {

    @Value("${telegram.bot.token}")
    private String token;
    private final RestTemplate restTemplate;
    
    @Override
    public void send(Reminder reminder) {
        String chatId = reminder.getUser().getTelegramId();
        
        if (chatId.isBlank()) {
            log.info("No TelegramId for user {}", reminder.getUser().getEmail());
            return;
        }
        
        String botUrl = "https://api.telegram.org/bot" + 
                token + "/sendMessage";
        
        Map<String, String> payload = Map.of(
            "chat_id", chatId,
            "text", reminder.getTitle() + "\n" + 
                    reminder.getDescription());
        
        try {
            restTemplate.postForObject(botUrl, 
                payload, String.class);
            log.info("Notification sent to Telegram of user {}", chatId);
        }
        catch (Exception e) {
            log.error("Error {} while sending to Telegram of user {}", 
                    e.getMessage(),chatId);
        }
    }
}