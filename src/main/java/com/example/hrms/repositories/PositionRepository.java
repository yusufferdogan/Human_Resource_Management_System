package com.example.hrms.repositories;

import com.example.hrms.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

    Position findPositionById(long position);

    Position findPositionByJobTitle(String name);
}
