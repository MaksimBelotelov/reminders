package org.belotelov.reminders.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReminderDto {
    
    private Long id;
    private String title;
    private String description;
    private LocalDateTime remind;
    
}
