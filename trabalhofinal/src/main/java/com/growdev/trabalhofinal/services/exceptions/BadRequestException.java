package com.growdev.trabalhofinal.services.exceptions;

public class BadRequestException  extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
