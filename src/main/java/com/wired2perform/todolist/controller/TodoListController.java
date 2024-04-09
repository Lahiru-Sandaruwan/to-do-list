/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:52 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.exception.ResourceNotFoundException;
import com.wired2perform.todolist.service.TodoListService;
import com.wired2perform.todolist.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private UserService userService;

    /**
     * Endpoint to create a new todo list.
     *
     * @param todoList The todo list to be created.
     * @return ResponseEntity containing the newly created todo list.
     */
    @PostMapping("/create")
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        // Get authenticated user
        UserAccount user = userService.getUserFromAuthentication();
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        TodoList newTodoList = todoListService.createTodoList(user, todoList);
        return new ResponseEntity<>(newTodoList, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get all todo lists for the authenticated user.
     *
     * @return ResponseEntity containing the list of todo lists.
     */
    @GetMapping
    public ResponseEntity<List<TodoList>> getUserTodoLists() {
        UserAccount user = userService.getUserFromAuthentication(); // Get authenticated user
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        List<TodoList> todoLists = todoListService.getUserTodoLists(user);
        return new ResponseEntity<>(todoLists, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing todo list.
     *
     * @param todoList      The updated list of todos.
     * @return ResponseEntity containing the updated todo list.
     */
    @PutMapping("/update")
    public ResponseEntity<TodoList> updateTodoList(@RequestBody TodoList todoList) {
        UserAccount user = userService.getUserFromAuthentication(); // Get authenticated user
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        TodoList updatedTodoList = todoListService.updateTodoList(user, todoList.getId(), todoList.getTodos());
        return new ResponseEntity<>(updatedTodoList, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a todo list.
     *
     * @param id The ID of the todo list to be deleted.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Endpoint to archive a todo list.
     *
     * @param todoListId The ID of the todo list to be archived.
     * @return ResponseEntity containing the archived todo list.
     */
    @PostMapping("/archive/{todoListId}")
    public ResponseEntity<TodoList> archiveTodoList(@PathVariable Long todoListId) {
        UserAccount user = userService.getUserFromAuthentication(); // Get authenticated user
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        TodoList archivedTodoList = todoListService.archiveTodoList(user, todoListId);
        if (archivedTodoList != null) {
            return new ResponseEntity<>(archivedTodoList, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Todo list not found");
        }
    }
}
