package org.belotelov.reminders.service;

import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.belotelov.reminders.entity.NotificationSender;
import org.belotelov.reminders.entity.Reminder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Getter
public class NotificationService {
    private final List<NotificationSender> senders;

    public NotificationService(List<NotificationSender> senders) {
        this.senders = senders;
    }
    
    public void notify(Reminder reminder) {
        for (NotificationSender sender : senders) {
            try {
                sender.send(reminder);
            }
            catch (Exception e) {
                log.error("Error while sending" + sender.getClass().getSimpleName());
            }
        }
    }
}
