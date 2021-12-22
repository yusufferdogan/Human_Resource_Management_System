package com.example.hrms.services.abtsracts;

import com.example.hrms.models.Position;

import java.util.List;

public interface PositionService {

    Position savePosition(Position position);

    List<Position> getAllPositions();

    Position updatePosition(Position position, Long id);

    Position deletePosition(Long id);

    Position findByName(String name);

    boolean isExistByName(String name);

}
