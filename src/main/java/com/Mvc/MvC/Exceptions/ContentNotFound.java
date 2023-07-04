package com.Mvc.MvC.Exceptions;

public class ContentNotFound  extends RuntimeException{

  private static final long serializableversionUID = 1;

    public ContentNotFound(String message) {
        super(message);
    }


}
