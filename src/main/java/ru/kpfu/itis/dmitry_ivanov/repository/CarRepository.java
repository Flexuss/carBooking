package ru.kpfu.itis.dmitry_ivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.dmitry_ivanov.entity.Car;

/**
 * Created by Dmitry on 22.05.2017.
 */
public interface CarRepository extends JpaRepository<Car, Long> {


    Car findByModel(String text);
}
