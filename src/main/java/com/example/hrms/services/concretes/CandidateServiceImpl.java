package com.example.hrms.services.concretes;

import com.example.hrms.models.Candidate;
import com.example.hrms.models.JobAdvertisement;
import com.example.hrms.repositories.CandidateRepository;
import com.example.hrms.repositories.JobAdvertisementRepository;
import com.example.hrms.services.abtsracts.CandidateService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository, JobAdvertisementRepository jobAdvertisementRepository) {
        this.candidateRepository = candidateRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate updateCandidate(Candidate candidate, Long id) {
        Optional<Candidate> updatedCandidate = candidateRepository.findById(id);
        if (updatedCandidate.isPresent()) {
            updatedCandidate.get().setName(candidate.getName());
            updatedCandidate.get().setBirthDate(candidate.getBirthDate());
            updatedCandidate.get().setEmailAddress(candidate.getEmailAddress());
            updatedCandidate.get().setSurname(candidate.getSurname());
            updatedCandidate.get().setTcNo(candidate.getTcNo());
            candidateRepository.save(updatedCandidate.get());
            System.out.println(updatedCandidate.get());
            return updatedCandidate.get();
        }
        return null;
    }

    @Override
    public Candidate deleteCandidate(Long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()) {
            candidateRepository.deleteById(id);
            return candidate.get();
        }
        return null;
    }

    @Override
    public Candidate findByTcNo(int tcNo) {
        return candidateRepository.findByTcNo(tcNo);
    }

    @Override
    public Candidate findByEmail(String email) {
        return candidateRepository.findByEmailAddress(email);
    }

    @Override
    public boolean isExistByEmail(String email) {
        return candidateRepository.findByEmailAddress(email) != null;
    }

    @Override
    public boolean isExistByTcNo(int tcNo) {
        return candidateRepository.findByTcNo(tcNo) != null;
    }

    @Override
    public Candidate applyJob(Long id, Long jobId) {
        Optional<JobAdvertisement> job = jobAdvertisementRepository.findById(jobId);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(job.isPresent() && candidate.isPresent()) {
            job.get().getCandidates().add(candidate.get());
            candidate.get().getApplied_jobs().add(job.get());
            jobAdvertisementRepository.save(job.get());
            candidateRepository.save(candidate.get());
            return candidate.get();
        }
        return null;
    }
}
