package com.example.hrms.services.abtsracts;


import com.example.hrms.models.City;
import com.example.hrms.models.Position;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {

    City saveCity(City city);

    List<City> getAllCity();

    City updateCity(City city, Long id);

    City deleteCity(Long id);

    City findByName(String name);

    boolean isExistByName(String name);

}
