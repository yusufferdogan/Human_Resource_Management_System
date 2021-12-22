package com.example.hrms.core.utilities.results;

import java.util.Collection;

public abstract class Result {
    private final boolean success;
    private String message;

    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, String message){
        this(success); // Yukarıdaki tek parametreli constructoru çağırmış oluyor!
        this.message = message;
    }

    public boolean isSuccess(){
        return this.success;
    }

    public String getMessage(){
        return this.message;
    }
}
