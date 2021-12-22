package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.JobAdvertisement;
import com.example.hrms.services.abtsracts.JobAdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job_advertisement")
public class JobAdvertisementController {

    private final JobAdvertisementService jobAdvertisementService;

    public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping
    ResponseEntity<?> getAllJobAdvertisements() {
        SuccessDataResult<List<JobAdvertisement>> results = new SuccessDataResult<>
                (this.jobAdvertisementService.getAllJobAdvertisements(), "All JobAdvertisements Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addJobAdvertisement(@RequestBody JobAdvertisement jobAdvertisement) {
        SuccessDataResult<JobAdvertisement> results = new SuccessDataResult<>
                (this.jobAdvertisementService.saveJobAdvertisement(jobAdvertisement), "New JobAdvertisement Successfully enrolled");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateJobAdvertisement(@PathVariable Long id, @RequestBody JobAdvertisement jobAdvertisement) {
        JobAdvertisement updatedJobAdvertisement = this.jobAdvertisementService.updateJobAdvertisement(jobAdvertisement, id);
        if (updatedJobAdvertisement == null) {
            return new ResponseEntity<>(new ErrorResult("JobAdvertisement does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<JobAdvertisement> results = new SuccessDataResult<>
                (updatedJobAdvertisement, "JobAdvertisement Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJobAdvertisement(@PathVariable Long id) {
        JobAdvertisement deleted = jobAdvertisementService.deleteJobAdvertisement(id);
        if (deleted == null) {
            return new ResponseEntity<>(new ErrorResult("JobAdvertisement does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<JobAdvertisement> results = new SuccessDataResult<>
                (deleted, "JobAdvertisement deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}/add-city")
    ResponseEntity<?> addCity(@PathVariable Long id, Long cityId) {
        JobAdvertisement updated = jobAdvertisementService.addCity(id, cityId);
        if (updated == null) {
            return new ResponseEntity<>(new ErrorResult("City Or JobAdvertisement does not exist"),
                    HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<JobAdvertisement> results = new SuccessDataResult<>
                (updated, "City added to Job Advertisement");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}/add-position")
    ResponseEntity<?> addPosition(@PathVariable Long id, Long positionId) {
        JobAdvertisement updated = jobAdvertisementService.addPosition(id, positionId);
        if (updated == null) {
            return new ResponseEntity<>(new ErrorResult("Position Or JobAdvertisement does not exist"),
                    HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<JobAdvertisement> results = new SuccessDataResult<>
                (updated, "Position added to Job Advertisement");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/activeJobs")
    ResponseEntity<?> getAllActiveJobAdvertisements() {
        List<JobAdvertisement> updated = jobAdvertisementService.getAllActiveJobAdvertisements();
        SuccessDataResult<List<JobAdvertisement>> results = new SuccessDataResult<>
                (updated, "All Active Job Advertisements Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/JobsOfCompany")
    ResponseEntity<?> getAllJobAdvertisementsOfCompany(String companyName) {
        List<JobAdvertisement> updated = jobAdvertisementService.getAllJobAdvertisementsOfCompany(companyName);
        SuccessDataResult<List<JobAdvertisement>> results = new SuccessDataResult<>
                (updated, "get All Job Advertisements Of Company");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/activeJobOfCompany")
    ResponseEntity<?> getAllActiveJobAdvertisementsOfCompany(String companyName) {
        List<JobAdvertisement> updated = jobAdvertisementService.getAllActiveJobAdvertisementsOfCompany(companyName);
        SuccessDataResult<List<JobAdvertisement>> results = new SuccessDataResult<>
                (updated, "get All Active JobAdvertisements Of Company");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
