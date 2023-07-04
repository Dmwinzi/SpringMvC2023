package com.Mvc.MvC.Exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject{
    int statuscode;
    String message;
    Date timestamp;
}
