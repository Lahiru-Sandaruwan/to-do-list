package com.wired2perform.todolist.service;

import com.wired2perform.todolist.entity.ArchivedTodoList;
import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.exception.ResourceNotFoundException;
import com.wired2perform.todolist.repository.ArchivedTodoListRepository;
import com.wired2perform.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoListServiceTest {

    @Mock
    private TodoListRepository todoListRepository;

    @Mock
    private ArchivedTodoListRepository archivedTodoListRepository;

    @InjectMocks
    private TodoListService todoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserTodoLists() {
        // Setup
        final UserAccount user = new UserAccount();
        user.setId(0L);
        user.setEmail("email");
        user.setPassword("password");
        user.setName("name");
        user.setTelephoneNumber("telephoneNumber");
        user.setAge(0);

        final TodoList todoList = new TodoList();
        todoList.setId(0L);
        todoList.setTodos(List.of("value"));
        todoList.setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        todoList.setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        user.setTodoLists(List.of(todoList));

        // Mock behavior
        when(todoListRepository.save(todoList)).thenReturn(todoList);

        // Test
        TodoList result = todoListService.createTodoList(user, todoList);
        assertEquals(todoList, result);
    }


    @Test
    void testUpdateTodoList() {
        // Mock data
        UserAccount user = new UserAccount();
        Long todoListId = 1L;
        TodoList todo = new TodoList();
        List<String> updatedTodos = List.of("Task 1", "Task 2");

        // Mock behavior
        when(todoListRepository.findByUserAndId(user, todoListId)).thenReturn(todo);
        when(todoListRepository.save(todo)).thenReturn(todo);

        // Test
        TodoList result = todoListService.updateTodoList(user, todoListId, updatedTodos);
        assertEquals(todo, result);
        assertEquals(updatedTodos, result.getTodos());
    }

    @Test
    void testDeleteTodoList() {
        // Mock data
        Long todoListId = 1L;

        // Mock behavior
        doNothing().when(todoListRepository).deleteById(todoListId);

        // Test
        assertDoesNotThrow(() -> todoListService.deleteTodoList(todoListId));
    }

    @Test
    void testDeleteTodoList_NotFound() {
        // Mock data
        Long todoListId = 1L;

        // Mock behavior
        doThrow(EmptyResultDataAccessException.class).when(todoListRepository).deleteById(todoListId);

        // Test
        assertThrows(ResourceNotFoundException.class, () -> todoListService.deleteTodoList(todoListId));
    }

    @Test
    void testArchiveTodoList() {
        // Mock data
        UserAccount user = new UserAccount();
        Long todoListId = 1L;
        TodoList todo = new TodoList();

        // Mock behavior
        when(todoListRepository.findByUserAndId(user, todoListId)).thenReturn(todo);
        when(archivedTodoListRepository.save(any(ArchivedTodoList.class))).thenReturn(new ArchivedTodoList());

        // Test
        TodoList result = todoListService.archiveTodoList(user, todoListId);
        assertEquals(todo, result);
        verify(todoListRepository, times(1)).delete(todo);
    }
}
