package org.belotelov.reminders.service;

import org.belotelov.reminders.entity.NotificationSender;
import org.belotelov.reminders.entity.Reminder;

public class TelegramNotificationSender implements NotificationSender {

    @Override
    public void send(Reminder reminder) {
        System.out.println("Telegram sender was called");
    }
}
