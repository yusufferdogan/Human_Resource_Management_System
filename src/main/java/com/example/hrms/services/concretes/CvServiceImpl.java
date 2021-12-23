package com.example.hrms.services.concretes;

import com.example.hrms.core.utilities.IsNotNull;
import com.example.hrms.models.Cv;
import com.example.hrms.models.Experience;
import com.example.hrms.models.Technology;
import com.example.hrms.repositories.CvRepository;
import com.example.hrms.repositories.ExperienceRepository;
import com.example.hrms.repositories.TechnologyRepository;
import com.example.hrms.services.abtsracts.CvService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CvServiceImpl implements CvService {
    
    private final CvRepository repository;
    private final ExperienceRepository experienceRepository;
    private final TechnologyRepository technologyRepository;

    public CvServiceImpl(CvRepository repository, ExperienceRepository experienceRepository, TechnologyRepository technologyRepository) {
        this.repository = repository;
        this.experienceRepository = experienceRepository;
        this.technologyRepository = technologyRepository;
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

//            if(IsNotNull.<Byte []>l(cv.getImage()))
//                updated.setImage(cv.getImage());

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

    @Override
    public Cv findById(Long id) {
        Optional<Cv> cv = repository.findById(id);
        return cv.orElse(null);
    }

    @Override
    public Cv uploadImage(Long id, MultipartFile file) throws IOException {
        Optional<Cv> cv = repository.findById(id);

        if(cv.isPresent()) {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            cv.get().setImage(byteObjects);
        }
        return null;
    }

    @Override
    public Cv addExperience(Long id, Long experienceId) {
        Optional<Cv> cv = repository.findById(id);
        Optional<Experience> experience = experienceRepository.findById(experienceId);
        if(cv.isPresent() && experience.isPresent()){
            cv.get().getExperiences().add(experience.get());
            experience.get().setCv(cv.get());
            repository.save(cv.get());
            experienceRepository.save(experience.get());
            return cv.get();
        }
        return null;
    }

    @Override
    public Cv addTechnology(Long id, Long technologyId) {
        Optional<Cv> cv = repository.findById(id);
        Optional<Technology> technology = technologyRepository.findById(technologyId);
        if(cv.isPresent() && technology.isPresent()){
            cv.get().getTechnologies().add(technology.get());
            technology.get().setCv_of_technology(cv.get());
            repository.save(cv.get());
            technologyRepository.save(technology.get());
            return  cv.get();
        }
        return null;
    }
}
