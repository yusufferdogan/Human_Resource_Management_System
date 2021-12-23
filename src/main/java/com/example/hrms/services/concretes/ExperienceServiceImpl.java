package com.example.hrms.services.concretes;

import com.example.hrms.models.Experience;
import com.example.hrms.repositories.ExperienceRepository;
import com.example.hrms.services.abtsracts.ExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository repository;

    public ExperienceServiceImpl(ExperienceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return repository.save(experience);
    }

    @Override
    public List<Experience> getAllExperience() {
        return repository.findAll();
    }

    @Override
    public Experience updateExperience(Experience experience, Long id) {
        Optional<Experience> updated = repository.findById(id);
        if(updated.isPresent()) {
            updated.get().setCompanyName(experience.getCompanyName());
            updated.get().setCurrentlyWorking(experience.getCurrentlyWorking());
            if(!updated.get().getCurrentlyWorking()){
                updated.get().setEndDate(experience.getEndDate());
            }
            updated.get().setPosition(experience.getPosition());
            updated.get().setStartDate(experience.getStartDate());
        }
        return null;
    }

    @Override
    public Experience deleteExperience(Long id) {
        Optional<Experience> updated = repository.findById(id);
        if(updated.isPresent()){
            repository.deleteById(id);
            return updated.get();
        }
        return null;
    }
}
