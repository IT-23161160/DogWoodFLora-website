package com.dogWoodFlora.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean isCurrentUser(Authentication authentication, Long userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            // Assuming UserEntity contains the ID in its details
            return userId.equals(((UserDetails) principal).getUsername());
        }
        return false;
    }
}
