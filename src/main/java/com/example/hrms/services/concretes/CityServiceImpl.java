package com.example.hrms.services.concretes;
import com.example.hrms.models.City;
import com.example.hrms.repositories.CityRepository;
import com.example.hrms.services.abtsracts.CityService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City updateCity(City city, Long id) {
        Optional<City> updatedCity = cityRepository.findById(id);
        if (updatedCity.isPresent()) {
            updatedCity.get().setName(city.getName());
            cityRepository.save(updatedCity.get());
            System.out.println(updatedCity.get());
            return updatedCity.get();
        }
        return null;
    }

    @Override
    public City deleteCity(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isPresent()){
            cityRepository.delete(city.get());
            return city.get();
        }
        return null;
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public boolean isExistByName(String name) {
        return cityRepository.findByName(name) != null;
    }


}
