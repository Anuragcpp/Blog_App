package com.blog.Blog_app.controller;

import com.blog.Blog_app.domain.dto.respose.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
@Slf4j
public class ErrorController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException exception
    ){
        log.error("Caught Exception : " + exception);
        ApiErrorResponse errro = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errro,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(
            IllegalStateException exception
    ) {
        log.error("Caught Exception : "+ exception);
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .status(HttpStatus.CONTINUE.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception exception
    ) {
        log.error("Caught Exception : " + exception);
        ApiErrorResponse errro = ApiErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errro,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
