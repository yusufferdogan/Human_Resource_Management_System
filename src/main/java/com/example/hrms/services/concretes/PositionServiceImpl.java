package com.example.hrms.services.concretes;

import com.example.hrms.models.Position;
import com.example.hrms.repositories.PositionRepository;
import com.example.hrms.services.abtsracts.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(com.example.hrms.repositories.PositionRepository pRepository) {
        positionRepository = pRepository;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Position updatePosition(Position positionDetails, Long id) {
        System.out.println();
        Optional<Position> updatedPosition = positionRepository.findById(id);
        if (updatedPosition.isPresent()) {
            updatedPosition.get().setJobTitle(positionDetails.getJobTitle());
            updatedPosition.get().setMaxSalary(positionDetails.getMaxSalary());
            updatedPosition.get().setMinSalary(positionDetails.getMinSalary());
            positionRepository.save(updatedPosition.get());
            System.out.println(updatedPosition.get());
            return updatedPosition.get();
        }
        return null;
    }

    @Override
    public Position deletePosition(Long id) {
        Optional<Position> position = positionRepository.findById(id);
        positionRepository.deleteById(id);
        return position.get();
    }

    @Override
    public Position findByName(String name) {
        return positionRepository.findPositionByJobTitle(name);
    }

    @Override
    public boolean isExistByName(String name) {
        return positionRepository.findPositionByJobTitle(name) != null;

    }

    @Override
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
}
