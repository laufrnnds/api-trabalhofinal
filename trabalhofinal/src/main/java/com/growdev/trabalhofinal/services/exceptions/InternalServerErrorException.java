package com.growdev.trabalhofinal.services.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String msg){
        super(msg);
    }

}