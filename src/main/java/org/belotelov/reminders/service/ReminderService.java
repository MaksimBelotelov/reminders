package org.belotelov.reminders.service;

import lombok.Data;
import org.belotelov.reminders.dto.ReminderDto;
import org.belotelov.reminders.entity.Reminder;
import org.belotelov.reminders.entity.User;
import org.belotelov.reminders.repository.ReminderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Data
public class ReminderService {
    
    private ReminderRepository reminderRepository;
    
    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }
    
    public ReminderDto createReminder(User user, ReminderDto dto) {
        Reminder reminder = new Reminder();
        reminder.setTitle(dto.getTitle());
        reminder.setDescription(dto.getDescription());
        reminder.setRemind(dto.getRemind());
        reminder.setUser(user);
        
        return toDto(reminderRepository.save(reminder));
    } 
    
    public Page<ReminderDto> findAll(User user, Pageable pageable) {
        return reminderRepository.findByUser(user, pageable).map(this::toDto);
    }
    
    public ReminderDto updateReminder(User user, ReminderDto dto, Long id) {
        Reminder reminder = reminderRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("No such reminder"));
        
        reminder.setTitle(dto.getTitle());
        reminder.setDescription(dto.getDescription());
        reminder.setRemind(dto.getRemind());
        
        return toDto(reminderRepository.save(reminder));
    }
    
    public void delete(Long id, User user) {
        Reminder reminder = reminderRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("No such reminder"));
        
        reminderRepository.delete(reminder);
    }
    
    private ReminderDto toDto(Reminder reminder) {
        ReminderDto dto = new ReminderDto();
        dto.setId(reminder.getId());
        dto.setTitle(reminder.getTitle());
        dto.setDescription(reminder.getDescription());
        dto.setRemind(reminder.getRemind());
        
        return dto;
    }
}
