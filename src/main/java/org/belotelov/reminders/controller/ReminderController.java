package org.belotelov.reminders.controller;

import org.belotelov.reminders.dto.ReminderDto;
import org.belotelov.reminders.service.ReminderService;
import org.belotelov.reminders.service.UserContextService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final UserContextService userContextService;

    public ReminderController(ReminderService reminderService, UserContextService userContextService) {
        this.reminderService = reminderService;
        this.userContextService = userContextService;
    }
    
    @PostMapping("/create")
    public ReminderDto create(@RequestBody ReminderDto dto) {
        
        return reminderService.
                createReminder(userContextService.getCurrentUser(), 
                               dto);
    }
    
    @GetMapping("/list")
    public Page<ReminderDto> read(@RequestParam int page, 
                                  @RequestParam int size) {
        
        return reminderService.findAll(userContextService.getCurrentUser(), 
                PageRequest.of(page, size));
    }
    
    @PostMapping("/update")
    public ReminderDto update(@RequestParam Long id, 
                              @RequestBody ReminderDto dto) {
        
        return reminderService.updateReminder(userContextService.getCurrentUser(), 
                dto, id);
    }
    
    @GetMapping("/delete")
    public void delete(@RequestParam Long id) {
        reminderService.delete(id, userContextService.getCurrentUser());
    }
    
    @GetMapping("/sort")
    public Page<ReminderDto> sort(@RequestParam String by,
                                  @RequestParam int page,
                                  @RequestParam int size) {
        
        Pageable pageable;
        switch (by.toLowerCase()) {
            case "title" -> pageable = PageRequest.of(page, 
                    size, Sort.by("title"));
            case "date", "time" -> pageable = PageRequest.of(page,
                    size, Sort.by("remind"));
            default -> throw new IllegalArgumentException("Parameter \"by\" should be \"title\" | \"date\" | \"time\"");
        }
        
        return reminderService.findAll(userContextService.getCurrentUser(), 
                pageable);
    }
}
