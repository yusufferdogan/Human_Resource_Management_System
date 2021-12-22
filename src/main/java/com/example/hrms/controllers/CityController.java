package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.City;
import com.example.hrms.services.abtsracts.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    ResponseEntity<?> getAllCities(){
        SuccessDataResult<List<City>> results =  new SuccessDataResult<>
                (this.cityService.getAllCity(),"All Cities Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addCity(@RequestBody City city){

        if(cityService.isExistByName(city.getName())){
            return new ResponseEntity<>(new ErrorResult("City already Exist"), HttpStatus.NOT_ACCEPTABLE);
        }
        SuccessDataResult<City> results =  new SuccessDataResult<>
                (this.cityService.saveCity(city),"New City Added");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody City city){
        City updatedCity = this.cityService.updateCity(city,id);
        if(updatedCity == null){
            return new ResponseEntity<>(new ErrorResult("City does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<City> results =  new SuccessDataResult<>
                (updatedCity,"City Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCity(@PathVariable Long id){
        City deleted = cityService.deleteCity(id);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("City does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<City> results =  new SuccessDataResult<>
                (deleted,"City deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
