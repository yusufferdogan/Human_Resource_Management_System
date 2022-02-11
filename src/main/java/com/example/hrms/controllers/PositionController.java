package com.example.hrms.controllers;
import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.City;
import com.example.hrms.models.Position;
import com.example.hrms.services.abtsracts.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    ResponseEntity<?> getAllPositions(){
        SuccessDataResult<List<Position>> results =  new SuccessDataResult<>
                (this.positionService.getAllPositions(),"All positions Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addPosition(@RequestBody Position position){
        if(positionService.isExistByName(position.getJobTitle())){
            return new ResponseEntity<>(new ErrorResult("position already Exist"), HttpStatus.NOT_ACCEPTABLE);
        }
        SuccessDataResult<Position> results =  new SuccessDataResult<>
                (this.positionService.savePosition(position),"New City Added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePosition(@PathVariable Long id,@RequestBody Position position){
        Position updatePosition = this.positionService.updatePosition(position,id);
        if(updatePosition == null){
            return new ResponseEntity<>(new ErrorResult("Position does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Position> results =  new SuccessDataResult<>
                (updatePosition,"Position Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePosition(@PathVariable Long id){
        Position deleted = positionService.deletePosition(id);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("Position does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<Position> results =  new SuccessDataResult<>
                (deleted,"Position deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);    }
}
