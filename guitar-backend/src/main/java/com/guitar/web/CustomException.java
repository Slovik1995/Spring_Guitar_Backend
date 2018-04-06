package com.guitar.web;

public class CustomException extends Exception {

    private ErrorInfo errorInfo;

    public CustomException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    @Override
    public String getMessage() {
        return "code: "+ errorInfo.getCode() + ", description: " +errorInfo.getDescription();
    }
}
