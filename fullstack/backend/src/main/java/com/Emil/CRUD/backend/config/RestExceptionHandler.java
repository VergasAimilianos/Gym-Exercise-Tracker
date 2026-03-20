package com.Emil.CRUD.backend.config;


import com.Emil.CRUD.backend.dtos.ErrorDto;
import com.Emil.CRUD.backend.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice

public class RestExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException e) {
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorDto(e.getMessage()));
    }
}
