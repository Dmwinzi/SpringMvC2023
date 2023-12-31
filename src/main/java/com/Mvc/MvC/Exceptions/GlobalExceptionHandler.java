package com.Mvc.MvC.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ContentNotFound.class)
    public ResponseEntity<ErrorObject> handlenotfoundexception(ContentNotFound contentNotFound, WebRequest webRequest){
        ErrorObject  errorObject  = new ErrorObject();
        errorObject.setStatuscode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(contentNotFound.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }


}
