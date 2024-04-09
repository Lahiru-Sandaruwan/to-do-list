/**
 * Author : lahiru_p
 * Date : 4/8/2024
 * Time : 2:45 PM
 * Project Name : todolist
 */

package com.wired2perform.todolist.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}