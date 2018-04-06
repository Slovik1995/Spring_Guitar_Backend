package com.guitar.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum ErrorInfo {

    MAIL_ALREADY_EXISTS(5000, "Mail already exists", HttpStatus.CONFLICT),
    NICK_ALREADY_EXISTS(5001,"Nick already exists",HttpStatus.CONFLICT),
    USER_NOT_FOUND(5002,"User not found", HttpStatus.NOT_FOUND),
    SONG_NOT_FOUND(5003,"Song not found", HttpStatus.NOT_FOUND),
    SONG_ALREADY_EXISTS(5004,"Song already exists", HttpStatus.CONFLICT)
    ;




    private final int code;
    private final String description;
    private final HttpStatus status;

    ErrorInfo(int code, String description, HttpStatus status) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public HttpStatus getStatus() {
        return status;
    }
}
