package com.springboot.bikeservice.services.imp;

import com.springboot.bikeservice.entities.BikeEntity;
import com.springboot.bikeservice.models.Bike;
import com.springboot.bikeservice.repositories.BikeRepository;
import com.springboot.bikeservice.services.BikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Bike getBikeById(UUID id) {
        Optional<BikeEntity> bikeOptional = bikeRepository.findById(id);
        if (bikeOptional.isPresent()) {
            return modelMapper.map(bikeOptional.get(), Bike.class);

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Bike not found");
        }
    }

    @Override
    public List<Bike> getAllBike() {
       List<BikeEntity> bikeEntities = bikeRepository.findAll();
       List<Bike> bikesModels = new ArrayList<>();
       for (BikeEntity bikeEntity : bikeEntities) {
           bikesModels.add(modelMapper.map(bikeEntity, Bike.class));
       }
       return bikesModels;
    }

    @Override
    public Bike createBike(Bike bike) {
        BikeEntity bikeEntity = modelMapper.map(bike, BikeEntity.class);
        bikeEntity.setCreationDate(new Date());
        bikeEntity.setUpdatedDate(new Date());
        bikeEntity.setValid(true);

        return modelMapper.map(bikeRepository.save(bikeEntity), Bike.class);

    }

    @Override
    public List<Bike> getByUserId(UUID userId) {
        Optional<List<BikeEntity>> bikesOptional = bikeRepository.findByUserId(userId);
        List<Bike> bikesModels = new ArrayList<>();
        if (bikesOptional.isPresent()) {
            for (BikeEntity bikeEntity : bikesOptional.get()) {
                bikesModels.add(modelMapper.map(bikeEntity, Bike.class));
            }
            return bikesModels;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The user either doesnt exist or doesnt have bikes");
        }
    }
}
