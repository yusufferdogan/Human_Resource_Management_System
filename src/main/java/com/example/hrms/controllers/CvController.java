package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.Cv;
import com.example.hrms.services.abtsracts.CvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cv")
public class CvController {
    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @GetMapping
    ResponseEntity<?> getAllCv() {
        SuccessDataResult<List<Cv>> results = new SuccessDataResult<>
                (this.cvService.getAllCv(), "All Cities Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addCv(@RequestBody Cv cv) {
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (this.cvService.saveCv(cv), "New Cv Added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCv(@PathVariable Long id, @RequestBody Cv cv) {
        Cv updatedCv = this.cvService.updateCv(cv, id);
        if (updatedCv == null) {
            return new ResponseEntity<>(new ErrorResult("Cv does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (updatedCv, "Cv Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCv(@PathVariable Long id) {
        Cv deleted = cvService.deleteCv(id);
        if (deleted == null) {
            return new ResponseEntity<>(new ErrorResult("Cv does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (deleted, "Cv deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    ResponseEntity<?> uploadPhoto(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        Cv cv = cvService.findById(id);
        if (cv == null) {
            return new ResponseEntity<>(new ErrorResult("Cv does not exist"), HttpStatus.NOT_FOUND);
        }
        Cv updatedCv = null;
        try {
            updatedCv = this.cvService.uploadImage(id, file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResult("I/O exception occurred"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (updatedCv, "Cv Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}/addExperience")
    ResponseEntity<?> addExperience(@PathVariable Long id, Long experienceId) {
        Cv updated = cvService.addExperience(id, experienceId);
        if (updated == null) {
            return new ResponseEntity<>(new ErrorResult("Cv does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (updated, "experience added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}/addTechnology")
    ResponseEntity<?> addTechnology(@PathVariable Long id, @RequestBody List<String> techs) {
        Cv updated = cvService.findById(id);
        if (updated == null) {
            return new ResponseEntity<>(new ErrorResult("Cv does not exist"), HttpStatus.NOT_FOUND);
        }
        for (String tech : techs) {
            cvService.addTechnology(id, tech);
        }
        SuccessDataResult<Cv> results = new SuccessDataResult<>
                (updated, "Technology added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
