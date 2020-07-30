package com.dextra.snackbar.controller.dto;

import org.springframework.http.HttpStatus;

public class ApiError extends ApiResponse {

    private Integer statusCode;
    private String statusText;
    private String message;

    private ApiError(Integer statusCode, String statusText, String message) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
    }

    public static ApiError create(HttpStatus status, String message) {
        return new ApiError(status.value(), status.getReasonPhrase(), message);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
