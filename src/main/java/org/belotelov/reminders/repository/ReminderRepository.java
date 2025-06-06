package org.belotelov.reminders.repository;

import java.util.Optional;
import org.belotelov.reminders.entity.Reminder;
import org.belotelov.reminders.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository 
        extends CrudRepository<Reminder, Long> {

    Page<Reminder> findByUser(User user, Pageable pageable);
    Optional<Reminder> findByIdAndUser(Long id, User user);
}