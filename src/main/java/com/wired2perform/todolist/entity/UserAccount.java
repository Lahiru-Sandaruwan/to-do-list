/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 8:49 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String telephoneNumber;
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoList> todoLists;

}
