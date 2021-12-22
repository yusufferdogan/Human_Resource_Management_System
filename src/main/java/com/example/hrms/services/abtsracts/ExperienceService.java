package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Experience;

import java.util.List;

public interface ExperienceService {

    Experience saveExperience(Experience experience);

    List<Experience> getAllExperience();

    Experience updateExperience(Experience experience, Long id);

    Experience deleteExperience(Long id);
}
