/**
 * Author : lahiru_p
 * Date : 4/9/2024
 * Time : 10:55 AM
 * Project Name : todolist
 */

package com.wired2perform.todolist.exception;

public class ErrorResponse {
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
