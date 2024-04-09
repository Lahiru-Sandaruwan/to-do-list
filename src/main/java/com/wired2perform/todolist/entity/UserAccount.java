/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 8:49 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email // Ensures email format
    @Column(name = "email", unique = true) // Enforces unique email
    @NotEmpty // Not null and not empty
    private String email;

    @Size(min = 8) // Minimum length of 8 characters
    @Column(name = "password")
    private String password;

    @NotEmpty // Not null and not empty
    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^\\d{10}$") // Ensures 10 digit phone number format
    @Column(name = "telephone_number", unique = true) // Enforces unique phone number
    private String telephoneNumber;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoList> todoLists;

}
