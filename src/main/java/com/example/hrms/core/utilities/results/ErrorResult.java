package com.example.hrms.core.utilities.results;

import java.util.Collection;

public class ErrorResult extends Result {

    // İşlem Sonucu Başarısız!
    public ErrorResult(){
        super(false);
    }
    public ErrorResult(String message) {
        super(false, message);
    }

}