/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:53 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.repository;

import com.wired2perform.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
