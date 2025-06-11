package org.belotelov.reminders.controller;

import org.belotelov.reminders.dto.UpdateUserDto;
import org.belotelov.reminders.entity.User;
import org.belotelov.reminders.service.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserContextService userContextService;

    @Autowired
    public UserController(UserContextService userContextService) {
        this.userContextService = userContextService;
    }
    
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto dto) {
        
        User updated = userContextService.updateUser(dto);
        
        return  ResponseEntity.ok(updated);
    }
}
