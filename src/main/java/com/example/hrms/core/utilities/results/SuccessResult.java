package com.example.hrms.core.utilities.results;

import java.util.Collection;

public class SuccessResult extends Result {

    // İşlem Sonnucu Başarılı
    public SuccessResult(){
        super(true);
    }
    public SuccessResult(String message) {
        super(true, message);
    }

}