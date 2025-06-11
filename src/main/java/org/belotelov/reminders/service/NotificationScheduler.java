package org.belotelov.reminders.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.belotelov.reminders.entity.Reminder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationScheduler {
    private final ReminderService reminderService;
    private final NotificationService notificationService;

    public NotificationScheduler(ReminderService reminderService, NotificationService notificationService) {
        this.reminderService = reminderService;
        this.notificationService = notificationService;
    }
    
    @Scheduled(fixedRate = 60000)
    public void sendReminders() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusMinutes(1);
        
        List<Reminder> list = 
                reminderService.findByDatesWithoutUser(start, end);
        
        log.info("INFO: preparing notifications for {} - {} ", start, end);
        
        for (Reminder item : list) {
            notificationService.notify(item);
            log.info("Reminder {} sent to user: {}", 
                    item.getTitle(), item.getUser().getEmail());
        }
    }
}
