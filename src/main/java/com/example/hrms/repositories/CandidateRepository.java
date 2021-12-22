package com.example.hrms.repositories;

import com.example.hrms.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    Candidate findByEmailAddress(String emailAddress);

    Candidate findByTcNo(int TcNo);

}
