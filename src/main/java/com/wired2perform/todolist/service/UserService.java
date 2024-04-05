/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:53 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.service;

import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserAccount createUser(UserAccount user) {
        return userRepository.save(user);
    }
}
