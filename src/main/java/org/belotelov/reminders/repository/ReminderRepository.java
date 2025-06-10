package org.belotelov.reminders.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.belotelov.reminders.entity.Reminder;
import org.belotelov.reminders.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReminderRepository 
        extends CrudRepository<Reminder, Long> {

    Page<Reminder> findByUser(User user, Pageable pageable);
    Optional<Reminder> findByIdAndUser(Long id, User user);
    
    @Query("""
           SELECT r FROM Reminder r
           WHERE r.user = :user
           AND r.remind BETWEEN :start AND :end
           """)
    Page<Reminder> findByUserAndRemindBetween(
            @Param(value = "user") User currentUser, 
            @Param(value = "start") LocalDateTime bDate, 
            @Param(value = "end") LocalDateTime eDate, 
            Pageable pageable);
}