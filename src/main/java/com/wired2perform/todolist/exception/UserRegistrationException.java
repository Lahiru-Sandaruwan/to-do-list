/**
 * Author : lahiru_p
 * Date : 4/9/2024
 * Time : 10:51 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.exception;

public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message) {
        super(message);
    }

    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}