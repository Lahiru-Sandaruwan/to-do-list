/**
 * Author : lahiru_p
 * Date : 4/7/2024
 * Time : 12:30 PM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ArchivedTodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Original Todo List ID cannot be null")
    @Column(name = "original_todo_list_id")
    private Long originalTodoListId;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "archived_date")
    private LocalDateTime archivedDate;
}
