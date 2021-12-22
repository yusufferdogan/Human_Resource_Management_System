package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.Candidate;
import com.example.hrms.services.abtsracts.CandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    ResponseEntity<?> getAllCandidates(){
        SuccessDataResult<List<Candidate>> results =  new SuccessDataResult<>
                (this.candidateService.getAllCandidates(),"All Candidates Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addCandidate(@RequestBody Candidate candidate){
        if(candidateService.isExistByEmail(candidate.getEmailAddress())){
            return new ResponseEntity<>(new ErrorResult("Account with Email already Exist"), HttpStatus.NOT_ACCEPTABLE);
        }
        if(candidateService.isExistByTcNo(candidate.getTcNo())){
            return new ResponseEntity<>(new ErrorResult("Account with TC NO already Exist"), HttpStatus.NOT_ACCEPTABLE);
        }
        SuccessDataResult<Candidate> results =  new SuccessDataResult<>
                (this.candidateService.saveCandidate(candidate),"New Candidate Successfully enrolled");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate){
        Candidate updatedCandidate = this.candidateService.updateCandidate(candidate,id);
        if(updatedCandidate == null){
            return new ResponseEntity<>(new ErrorResult("Candidate does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Candidate> results =  new SuccessDataResult<>
                (updatedCandidate,"Candidate Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCandidate(@PathVariable Long id){
        Candidate deleted = candidateService.deleteCandidate(id);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("Candidate does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Candidate> results =  new SuccessDataResult<>
                (deleted,"Candidate deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("/{id}/apply-job")
    ResponseEntity<?> applyJob(@PathVariable Long id,Long jobId){
        Candidate deleted = candidateService.applyJob(id,jobId);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("Job does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Candidate> results =  new SuccessDataResult<>
                (deleted,"Job Applied");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
