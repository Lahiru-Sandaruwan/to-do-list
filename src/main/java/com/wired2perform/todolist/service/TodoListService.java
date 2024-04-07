/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 9:12 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.service;

import com.wired2perform.todolist.entity.ArchivedTodoList;
import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.repository.ArchivedTodoListRepository;
import com.wired2perform.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private ArchivedTodoListRepository archivedTodoListRepository;

    public List<TodoList> getUserTodoLists(UserAccount user) {
        return todoListRepository.findByUser(user);
    }

    public TodoList createTodoList(UserAccount user, TodoList todoList) {
        todoList.setUser(user);
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(UserAccount user, Long todoListId, List<String> todos) {
        TodoList todoList = todoListRepository.findByUserAndId(user, todoListId);
        if (todoList != null) {
            todoList.setTodos(todos);
            return todoListRepository.save(todoList);
        }
        return null;
    }
    public void deleteTodoList( Long id) {
        todoListRepository.deleteById(id);
    }

    public TodoList archiveTodoList(UserAccount user, Long id) {
        TodoList todoList = todoListRepository.findByUserAndId(user, id);
        if (todoList != null) {
            ArchivedTodoList archivedTodoList = new ArchivedTodoList();
            archivedTodoList.setOriginalTodoListId(todoList.getId());
            archivedTodoList.setUserId(user.getId());
            archivedTodoList.setArchivedDate(LocalDateTime.now());
            archivedTodoListRepository.save(archivedTodoList);

            todoListRepository.delete(todoList);
            return todoList;
        }
        return null;
    }
}
