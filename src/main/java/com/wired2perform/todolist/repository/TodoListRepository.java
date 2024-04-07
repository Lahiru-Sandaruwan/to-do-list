/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:53 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.repository;

import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByUser(UserAccount user);

    TodoList findByUserAndId(UserAccount user, Long todoListId);
}
