package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Cv;

import java.util.List;

public interface CvService {

    Cv saveCv(Cv cv);

    List<Cv> getAllCv();

    Cv updateCv(Cv cv, Long id);

    Cv deleteCv(Long id);
}
