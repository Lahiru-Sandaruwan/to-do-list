/**
 * Author : lahiru_p
 * Date : 4/7/2024
 * Time : 12:33 PM
 * Project Name : todolist
 */

package com.wired2perform.todolist.repository;

import com.wired2perform.todolist.entity.ArchivedTodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivedTodoListRepository extends JpaRepository<ArchivedTodoList, Long> {
}
