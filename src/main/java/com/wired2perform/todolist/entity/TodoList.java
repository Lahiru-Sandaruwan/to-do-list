/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:54 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "to_do_list")
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Add this annotation to prevent infinite recursion
    private UserAccount user;

    @ElementCollection//used to manage a collection of values within an entity without the need for a separate table in the database.
    @Column(name = "todos")
    @NotEmpty(message = "Todo list cannot be empty")
    private List<String> todos;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
