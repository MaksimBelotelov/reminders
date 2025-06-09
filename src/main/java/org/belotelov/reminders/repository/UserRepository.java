package org.belotelov.reminders.repository;

import java.util.Optional;
import org.belotelov.reminders.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}