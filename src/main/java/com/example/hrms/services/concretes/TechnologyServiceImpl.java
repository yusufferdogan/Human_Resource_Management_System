package com.example.hrms.services.concretes;

import com.example.hrms.models.Technology;
import com.example.hrms.repositories.TechnologyRepository;
import com.example.hrms.services.abtsracts.TechnologyService;

import java.util.List;
import java.util.Optional;

public class TechnologyServiceImpl implements TechnologyService {
    
    private final TechnologyRepository repository;

    public TechnologyServiceImpl(TechnologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Technology saveTechnology(Technology city) {
        return repository.save(city);
    }

    @Override
    public List<Technology> getAllTechnology() {
        return repository.findAll();
    }

    @Override
    public Technology updateTechnology(Technology city, Long id) {
        Optional<Technology> updatedTechnology = repository.findById(id);
        if (updatedTechnology.isPresent()) {
            updatedTechnology.get().setName(city.getName());
            repository.save(updatedTechnology.get());
            System.out.println(updatedTechnology.get());
            return updatedTechnology.get();
        }
        return null;
    }

    @Override
    public Technology deleteTechnology(Long id) {
        Optional<Technology> city = repository.findById(id);

        if(city.isPresent()){
            repository.delete(city.get());
            return city.get();
        }
        return null;
    }
}
