package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Technology;

import java.util.List;

public interface TechnologyService {

    Technology saveTechnology(Technology technology);

    List<Technology> getAllTechnology();

    Technology updateTechnology(Technology technology, Long id);

    Technology deleteTechnology(Long id);
}
