/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:52 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to register a new user.
     *
     * @param user The user account details to be registered.
     * @return ResponseEntity containing the newly created user account.
     */
    @PostMapping("/register")
    public ResponseEntity<UserAccount> registerUser(@RequestBody UserAccount user) {
        try {
            UserAccount newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}