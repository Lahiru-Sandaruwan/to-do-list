/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:54 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ElementCollection
    //used to manage a collection of values within an entity without the need for a separate table in the database.
    private List<String> todos;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
