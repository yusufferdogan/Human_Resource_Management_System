package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Candidate;
import com.example.hrms.models.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {

    Candidate saveCandidate(Candidate candidate);

    List<Candidate> getAllCandidates();

    Candidate updateCandidate(Candidate candidate, Long id);

    Candidate deleteCandidate(Long id);

    Candidate findByTcNo(int tcNo);

    Candidate findByEmail(String email);

    boolean isExistByEmail(String email);

    boolean isExistByTcNo(int tcNo);

    Candidate applyJob(Long id, Long jobId);
}
