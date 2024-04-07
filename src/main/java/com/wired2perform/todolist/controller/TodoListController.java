/**
 * Author : lahiru_p
 * Date : 4/5/2024
 * Time : 10:52 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.controller;

import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.service.TodoListService;
import com.wired2perform.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        // Get authenticated user
        UserAccount user = userService.getUserFromAuthentication();
        TodoList newTodoList = todoListService.createTodoList(user, todoList);
        return new ResponseEntity<>(newTodoList, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoList>> getUserTodoLists() {
        UserAccount user = userService.getUserFromAuthentication(); // Get authenticated user
        List<TodoList> todoLists = todoListService.getUserTodoLists(user);
        return new ResponseEntity<>(todoLists, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TodoList> updateTodoList( @RequestParam("todoListId") Long todoListId, @RequestBody List<String> todos) {
        UserAccount user = userService.getUserFromAuthentication(); // Get authenticated user
        TodoList updatedTodoList = todoListService.updateTodoList(user, todoListId, todos);
        return new ResponseEntity<>(updatedTodoList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/archive/{todoListId}")
    public ResponseEntity<TodoList> archiveTodoList(@PathVariable Long todoListId) {
        UserAccount user =  userService.getUserFromAuthentication(); // Get authenticated user
        if (user != null) {
            TodoList archivedTodoList = todoListService.archiveTodoList(user, todoListId);
            if (archivedTodoList != null) {
                return new ResponseEntity<>(archivedTodoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
