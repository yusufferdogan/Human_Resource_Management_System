package com.example.hrms.services.concretes;

import com.example.hrms.core.utilities.IsNotNull;
import com.example.hrms.models.Cv;
import com.example.hrms.models.Cv;
import com.example.hrms.repositories.CvRepository;
import com.example.hrms.services.abtsracts.CvService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CvServiceImpl implements CvService {
    
    private final CvRepository repository;

    public CvServiceImpl(CvRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cv saveCv(Cv cv) {
        return repository.save(cv);
    }

    @Override
    public List<Cv> getAllCv() {
        return repository.findAll();
    }

    @Override
    public Cv updateCv(Cv cv, Long id) {
        Optional<Cv> updatedCv = repository.findById(id);
        System.out.println("updatedCv: " + updatedCv);
        if (updatedCv.isPresent()) {
            Cv updated = updatedCv.get();
            System.out.println("updated at the begining : " + updated);

            if(IsNotNull.<String>l(cv.getSchoolName()))
                updated.setSchoolName(cv.getSchoolName());

            if(IsNotNull.<String>l(cv.getDepartment()))
                updated.setDepartment(cv.getDepartment());

            if(IsNotNull.<String>l(cv.getCoverLetter()))
                updated.setCoverLetter(cv.getCoverLetter());

            if(IsNotNull.<String>l(cv.getGithubLink()))
                updated.setGithubLink(cv.getGithubLink());

            if(IsNotNull.<String>l(cv.getStackOverFlowLink()))
                updated.setStackOverFlowLink(cv.getStackOverFlowLink());

            if(IsNotNull.<LocalDate>l(cv.getGraduationYear()))
                updated.setGithubLink(cv.getGithubLink());

            if(IsNotNull.<Byte []>l(cv.getImage()))
                updated.setImage(cv.getImage());

            repository.save(updated);
            System.out.println(updatedCv.get());
            return updatedCv.get();
        }
        return null;
    }

    @Override
    public Cv deleteCv(Long id) {
        Optional<Cv> cv = repository.findById(id);

        if(cv.isPresent()){
            repository.delete(cv.get());
            return cv.get();
        }
        return null;
    }
}
