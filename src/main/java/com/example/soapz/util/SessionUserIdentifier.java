package com.example.soapz.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SessionUserIdentifier {
    private String currentUserEmail;

    public String getCurrentUserEmail() {
        if (currentUserEmail == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                currentUserEmail = ((UserDetails) principal).getUsername();
            } else {
                throw new IllegalStateException("Current user is not authenticated");
            }
        }
        return currentUserEmail;
    }
}
