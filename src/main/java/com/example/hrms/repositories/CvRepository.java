package com.example.hrms.repositories;

import com.example.hrms.models.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<Cv,Long> {
}
