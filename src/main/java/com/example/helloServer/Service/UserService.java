package com.example.helloServer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.helloServer.Exception.AuthException;
import com.example.helloServer.Model.User;
import com.example.helloServer.Repository.UserRepository;
import com.example.helloServer.utils.JwtUtil;
import com.example.helloServer.validator.AuthValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthValidator authValidator;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new AuthException("Name is required");
        }
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new AuthException("UserName is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new AuthException("email is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new AuthException("password is required");
        }
        if (user.getRole() <= 0 || user.getRole() > 3) {
            throw new AuthException("Role Entered is not correct");
        }
        if (!authValidator.isValidUsername(user.getUserName())) {

            throw new AuthException("the user name must contains only Lowercase , uppercase, and numbers and _");
        }

        if (!authValidator.isValidEmail(user.getEmail())) {
            throw new AuthException("Email is not valid...");
        }

        if (authValidator.isValidPassword(user.getPassword()) != "VALID") {
            throw new AuthException(authValidator.isValidPassword(user.getPassword()));
        }

        if (userRepository.existsByUserName(user.getUserName())) {
            throw new AuthException("Username already in use");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AuthException("Email already in use");
        }

        // password hashing here , bcrypt is hashing the apssword here
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // if all above is done save the thing in the db here
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
