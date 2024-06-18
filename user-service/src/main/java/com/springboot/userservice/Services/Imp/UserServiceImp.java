package com.springboot.userservice.Services.Imp;

import com.springboot.userservice.Commands.Request.UpdateUserRequest;
import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Commands.Response.UserResponse;
import com.springboot.userservice.Entities.UserEntity;
import com.springboot.userservice.Models.Bike;
import com.springboot.userservice.Models.Car;
import com.springboot.userservice.Repositories.UserRepository;
import com.springboot.userservice.Services.UserService;
import com.springboot.userservice.feignClients.BikeFeignClient;
import com.springboot.userservice.feignClients.carFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private carFeignClient carFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;



    @Override
    public List<UserResponse> findAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        userRepository.findAll().forEach(user -> userResponses.add(modelMapper.map(user, UserResponse.class)));
        return userResponses;
    }

    @Override
    public UserResponse findById(UUID id) {

        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return modelMapper.map(userEntity.get(), UserResponse.class);

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There isnt a client with that id");
        }
    }



    @Override
    public UserResponse findByEmail(String email) {

       Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
       if (userEntityOptional.isPresent()) {
           return modelMapper.map(userEntityOptional.get(), UserResponse.class);
       }else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"there isnt a client with that email");

       }

    }

    @Override
    public UserResponse updateUser(UpdateUserRequest UpdateUserRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(UpdateUserRequest.getId());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setName(UpdateUserRequest.getName());
            userEntity.setEmail(UpdateUserRequest.getEmail());
            userEntity.setUpdatedDate(new Date());
            userRepository.save(userEntity);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There isnt a client with that id");
        }
        return modelMapper.map(userEntityOptional.get(), UserResponse.class);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
       UserEntity userEntity = new UserEntity();
       userEntity.setName(userRequest.getName());
       userEntity.setEmail(userRequest.getEmail());
       userEntity.setUpdatedDate(new Date());
       userEntity.setCreationDate(new Date());
       userRepository.save(userEntity);
       return modelMapper.map(userEntity, UserResponse.class);

    }

    @Override
    public UserResponse deleteUser(UUID id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setValid(false);
            userRepository.save(userEntity);
            return modelMapper.map(userEntity, UserResponse.class);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There isnt a client with that id");
        }
    }

    @Override
    public List getCars(UUID userId){

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt.getTokenValue());
            ResponseEntity<List> cars = restTemplate .exchange("http://car-service/cars/user/"+userId, HttpMethod.GET,new  HttpEntity<>(headers), List.class);
            return cars.getBody();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There isnt a client with that id");
        }


    }
    @Override
    public List<Bike> getBikes(UUID userId){

        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isPresent()) {
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt.getTokenValue());
            ResponseEntity<List> bikes = restTemplate .exchange("http://bike-service/bikes/user/"+userId, HttpMethod.GET,new  HttpEntity<>(headers), List.class);
            return bikes.getBody();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There isnt a client with that id");
        }

    }


    @Override
    public Car saveCar(Car car) {
        return carFeignClient.saveCar(car);
    }

    @Override
    public Bike saveBike(Bike bike){
        return bikeFeignClient.saveBike(bike);
    }

    @Override
    public Map<String, Object> findVehiclesByUserId(UUID id) {
        Map<String,Object> result = new HashMap<>();
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null){
            result.put("message","the user doesnt exist");
            return result;
        }
        List<Car> cars = carFeignClient.findCarsByUserId(id);
        if(cars.isEmpty()){
            result.put("message","the user doesnt have any cars");

        }else{
            result.put("Cars",cars);
        }
        List<Bike> bikes = bikeFeignClient.findBikesByUserId(id);
        if(bikes.isEmpty()){
            result.put("message","the user doesnt have any bikes");

        }else{
            result.put("Bikes",bikes);
        }
        return result;
    }




}
