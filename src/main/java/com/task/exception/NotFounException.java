package com.task.exception;

public class NotFounException extends RuntimeException {


    public NotFounException(String msg){

        super("Error is :"+msg);
    }
}
