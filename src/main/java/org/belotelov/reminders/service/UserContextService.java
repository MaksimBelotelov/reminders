package org.belotelov.reminders.service;

import org.belotelov.reminders.dto.UpdateUserDto;
import org.belotelov.reminders.entity.User;
import org.belotelov.reminders.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {
    
    private final UserRepository userRepository;

    public UserContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        
        if(authentication.getPrincipal() instanceof Jwt jwt) {
            String email = jwt.getClaim("email");
            String name = jwt.getClaim("name");
            
            return userRepository.findByEmail(email)
                    .orElseGet(() -> 
                            userRepository.save(new User(null, name, email, "", null)));
        }
        
        throw new RuntimeException("Unsupported principal type: " + authentication.getPrincipal());
    }
    
    public User updateUser(UpdateUserDto dto) {
        
        User user = getCurrentUser();
        
        if (dto.getUsername() != null) { 
            user.setUsername(dto.getUsername()); }
        if (dto.getTelegramId() != null) { 
            user.setTelegramId(dto.getTelegramId()); }
        
        return userRepository.save(user);
    }
}