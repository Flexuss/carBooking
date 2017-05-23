package ru.kpfu.itis.dmitry_ivanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitry_ivanov.entity.Car;
import ru.kpfu.itis.dmitry_ivanov.repository.CarRepository;

import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */

@Service
public class CarServiceImpl implements CarService {


    @Autowired
    CarRepository carRepository;


    @Override
    public Car getCar(String text) {
        return carRepository.findByModel(text);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void remove(Long id) {
        carRepository.delete(id);
    }

    @Override
    public Car getCarById(String id) {
        return carRepository.findOne(Long.valueOf(id));
    }
}
