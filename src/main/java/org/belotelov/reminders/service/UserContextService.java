package org.belotelov.reminders.service;

import org.belotelov.reminders.entity.User;
import org.belotelov.reminders.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserContextService {
    
    private final UserRepository userRepository;

    public UserContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getCurrentUser() {
        OAuth2User principal = (OAuth2User) (SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");
        
        return userRepository.findByEmail(email)
                .orElse(userRepository.save(new User(null, name, email, "", null)));        
    }
}