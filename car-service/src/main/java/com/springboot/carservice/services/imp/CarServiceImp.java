package com.springboot.carservice.services.imp;

import com.springboot.carservice.entities.CarEntity;
import com.springboot.carservice.models.Car;
import com.springboot.carservice.repositories.CarRepository;
import com.springboot.carservice.services.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CarServiceImp implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Car getCarById(UUID id) {
        Optional<CarEntity> carEntity = carRepository.findById(id);
        if (carEntity.isPresent()) {
            return modelMapper.map(carEntity.get(), Car.class);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
        }
    }

    @Override
    public List<Car> getAllCars() {
       List<Car> carsModels = new ArrayList<>();
       carRepository.findAll().forEach(car -> carsModels.add(modelMapper.map(car, Car.class)));
       return carsModels;

    }

    @Override
    public Car createCar(Car car) {
        CarEntity carEntity = modelMapper.map(car, CarEntity.class);
        carEntity.setValid(true);
        carEntity.setCreationDate(new Date());
        carEntity.setUpdatedDate(new Date());

        return modelMapper.map(carRepository.save(carEntity), Car.class);
    }

    @Override
    public List<Car> getCarsByUserId(UUID userId) {
        Optional<List<CarEntity>> carEntity = carRepository.findByUserId(userId);
        List<Car> carsModels = new ArrayList<>();
        if (carEntity.isPresent()) {
            for (CarEntity carEntity1 : carEntity.get()) {
                carsModels.add(modelMapper.map(carEntity1, Car.class));
            }
            return carsModels;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cars not found");
        }
    }

}
