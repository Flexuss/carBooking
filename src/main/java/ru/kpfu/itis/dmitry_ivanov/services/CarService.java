package ru.kpfu.itis.dmitry_ivanov.services;

import ru.kpfu.itis.dmitry_ivanov.entity.Car;

import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */
public interface CarService {


    Car getCar(String text);

    List<Car> findAll();

    void save(Car car);

    void remove(Long id);

    Car getCarById(String id);
}
