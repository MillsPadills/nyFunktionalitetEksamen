package com.example.carrentalexam.services;

import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public void createNewCar(String frameNumber, String brand, String model, String registrationNumber, String status) {
        carRepository.createNewCar(frameNumber, brand, model, registrationNumber, status);
    }
}