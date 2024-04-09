/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 9:12 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.wired2perform.todolist.entity.ArchivedTodoList;
import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.exception.ResourceNotFoundException;
import com.wired2perform.todolist.repository.ArchivedTodoListRepository;
import com.wired2perform.todolist.repository.TodoListRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private ArchivedTodoListRepository archivedTodoListRepository;

    /**
     * Get all todo lists for a specific user.
     *
     * @param user The user for whom to retrieve todo lists.
     * @return List of todo lists belonging to the user.
     * @throws IllegalArgumentException If the provided user is null.
     */
    public List<TodoList> getUserTodoLists(UserAccount user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return todoListRepository.findByUser(user);
    }

    /**
     * Create a new todo list for a specific user.
     *
     * @param user     The user for whom to create the todo list.
     * @param todoList The todo list to be created.
     * @return The newly created todo list.
     * @throws IllegalArgumentException If the provided user or todoList is null.
     */
    public TodoList createTodoList(UserAccount user, TodoList todoList) throws IllegalArgumentException {
        if (user == null || todoList == null) {
            throw new IllegalArgumentException("User and todoList cannot be null");
        }

        todoList.setUser(user);
        return todoListRepository.save(todoList);
    }

    /**
     * Update an existing todo list for a specific user.
     *
     * @param user        The user who owns the todo list.
     * @param todoListId  The ID of the todo list to update.
     * @param todos       The updated list of todos.
     * @return The updated todo list.
     * @throws ResourceNotFoundException If the todo list is not found for the user.
     */
    public TodoList updateTodoList(UserAccount user, Long todoListId, List<String> todos) {
        TodoList todoList = todoListRepository.findByUserAndId(user, todoListId);
        if (todoList == null) {
            throw new ResourceNotFoundException("Todo list not found for the user");
        }
        todoList.setTodos(todos);
        return todoListRepository.save(todoList);
    }

    /**
     * Delete a todo list by ID.
     *
     * @param id The ID of the todo list to delete.
     * @throws ResourceNotFoundException If the todo list is not found.
     */
    public void deleteTodoList(Long id) {
        try {
            todoListRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Todo list not found");
        }
    }

    /**
     * Archive a todo list for a specific user.
     *
     * @param user The user who owns the todo list.
     * @param id   The ID of the todo list to archive.
     * @return The archived todo list.
     * @throws ResourceNotFoundException If the todo list is not found for the user.
     */
    public TodoList archiveTodoList(UserAccount user, Long id) {
        TodoList todoList = todoListRepository.findByUserAndId(user, id);
        if (todoList == null) {
            return null;
        }
        ArchivedTodoList archivedTodoList = new ArchivedTodoList();
        archivedTodoList.setOriginalTodoListId(todoList.getId());
        archivedTodoList.setUserId(user.getId());
        archivedTodoList.setArchivedDate(LocalDateTime.now());
        archivedTodoListRepository.save(archivedTodoList);

        todoListRepository.delete(todoList);
        return todoList;
    }
}