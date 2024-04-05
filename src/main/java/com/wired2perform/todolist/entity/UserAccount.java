/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 8:49 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String telephoneNumber;
    private int age;

    public UserAccount(String email, String password) {
    }
}
