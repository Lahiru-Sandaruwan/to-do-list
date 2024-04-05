/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:52 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.controller;

import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserAccount> registerUser(@RequestBody UserAccount user) {
        UserAccount newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // other endpoints for user management
}
