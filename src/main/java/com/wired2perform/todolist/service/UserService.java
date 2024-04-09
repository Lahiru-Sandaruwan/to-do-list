/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:53 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Create a new user account.
     *
     * @param user The user account to be created.
     * @return The newly created user account.
     * @throws Exception If there is an error while creating the user account.
     */
    public UserAccount createUser(UserAccount user) throws Exception {
        // Check for existing user with same email (optional)
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email address already in use.");
        }
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while creating user account.", e);
        }
    }

    /**
     * Retrieve the authenticated user from the security context.
     *
     * @return The authenticated user account, or null if user is not authenticated.
     */
    public UserAccount getUserFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return userRepository.findByEmail(user.getUsername());
        }
        return null; // Return null if user is not authenticated
    }
}
