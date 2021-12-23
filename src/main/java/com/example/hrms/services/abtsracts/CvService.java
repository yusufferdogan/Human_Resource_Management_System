package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Cv;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CvService {

    Cv saveCv(Cv cv);

    List<Cv> getAllCv();

    Cv updateCv(Cv cv, Long id);

    Cv deleteCv(Long id);

    Cv findById(Long id);

    Cv uploadImage(Long id, MultipartFile image) throws IOException;

    Cv addExperience(Long id,Long experienceId);

    Cv addTechnology(Long id,String name);
}
