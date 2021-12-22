package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.Experience;
import com.example.hrms.services.abtsracts.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExperienceController {
    
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    ResponseEntity<?> getAllCities(){
        SuccessDataResult<List<Experience>> results =  new SuccessDataResult<>
                (this.experienceService.getAllExperience(),"All Cities Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addExperience(@RequestBody Experience experience){
        SuccessDataResult<Experience> results =  new SuccessDataResult<>
                (this.experienceService.saveExperience(experience),"New Experience Added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateExperience(@PathVariable Long id, @RequestBody Experience experience){
        Experience updatedExperience = this.experienceService.updateExperience(experience,id);
        if(updatedExperience == null){
            return new ResponseEntity<>(new ErrorResult("Experience does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Experience> results =  new SuccessDataResult<>
                (updatedExperience,"Experience Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteExperience(@PathVariable Long id){
        Experience deleted = experienceService.deleteExperience(id);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("Experience does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Experience> results =  new SuccessDataResult<>
                (deleted,"Experience deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
