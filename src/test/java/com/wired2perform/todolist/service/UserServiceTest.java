package com.wired2perform.todolist.service;

import com.wired2perform.todolist.entity.TodoList;
import com.wired2perform.todolist.entity.UserAccount;
import com.wired2perform.todolist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Given
        UserAccount user = new UserAccount();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setId(1L);
        user.setName("John Doe");
        user.setTelephoneNumber("+1234567890");
        user.setAge(30);

        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        // When
        try {
            UserAccount createdUser = userService.createUser(user);

            // Then
            assertNotNull(createdUser);
            assertEquals(encodedPassword, createdUser.getPassword());
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testGetUserFromAuthentication() {
        // Given
        List<GrantedAuthority> authorities = Collections.emptyList();
        User expectedUser = new User("test@example.com", "password", authorities);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(expectedUser);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

        UserAccount expectedUserAccount = new UserAccount();
        expectedUserAccount.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(expectedUserAccount);

        // When
        UserAccount actualUserAccount = userService.getUserFromAuthentication();

        // Then
        assertNotNull(actualUserAccount);
        assertEquals(expectedUserAccount.getEmail(), actualUserAccount.getEmail());
    }
}
