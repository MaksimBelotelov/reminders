package org.belotelov.reminders.controller;

import org.belotelov.reminders.dto.ReminderDto;
import org.belotelov.reminders.entity.User;
import org.belotelov.reminders.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {
    
    private final ReminderService reminderService;
    
    @Autowired
    public ReminderController(ReminderService reminderService) {
       this.reminderService = reminderService;
    }
    
    private User getCurrentUser() {
        User user1 = new User(1L, "Testuser", "a@mail.com", "TelegaId", null);
        return user1;
    }
    
    
    @PostMapping("/create")
    public ReminderDto create(@RequestBody ReminderDto dto) {
        
        return reminderService.createReminder(getCurrentUser(), dto);
    }
    
    @GetMapping("/list")
    public Page<ReminderDto> findAll(@RequestParam int page, 
                                     @RequestParam int size) {
        
        return reminderService.findAll(getCurrentUser(), 
                PageRequest.of(page, size));
    }
    
//    @PostMapping("/update")
//    public Reminder update(@RequestParam Long id, 
//                           @RequestBody ReminderDto dto) {
//        
//        return reminderService.update(id, dto, getCurrentUser());
//    }
}
