package com.example.helloServer.validator;

import org.springframework.stereotype.Component;

@Component
public class AuthValidator {

    public boolean isValidUsername(String value) {
        if (value == null) {
            return false;
        }
        // her only uppercase, lowercase, numbers, and underscore, length 3–20
        return value.matches("^[A-Za-z0-9_]{3,20}$");
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    public String isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }

        // Minimum length
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }

        // Uppercase check
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }

        // Lowercase check
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter";
        }

        // Digit check
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit";
        }

        // Special character check
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return "Password must contain at least one special character (!@#$%^&* etc.)";
        }

      
        return "VALID";
    }

}
