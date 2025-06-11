package org.belotelov.reminders.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    
    private String username;
    private String telegramId;
}
