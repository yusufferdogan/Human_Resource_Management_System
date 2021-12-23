package com.example.hrms.repositories;

import com.example.hrms.models.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyRepository extends JpaRepository<Technology,Long> {
}
