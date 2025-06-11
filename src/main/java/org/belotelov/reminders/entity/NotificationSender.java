package org.belotelov.reminders.entity;

public interface NotificationSender {
    void send(Reminder reminder);
}