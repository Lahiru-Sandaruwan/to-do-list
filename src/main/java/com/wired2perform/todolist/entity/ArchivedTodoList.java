/**
 * Author : lahiru_p
 * Date : 4/7/2024
 * Time : 12:30 PM
 * Project Name : todolist
 */

package com.wired2perform.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ArchivedTodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originalTodoListId;
    private Long userId;
    private LocalDateTime archivedDate;
}
