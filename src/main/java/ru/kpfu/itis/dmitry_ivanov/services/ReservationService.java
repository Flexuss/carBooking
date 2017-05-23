package ru.kpfu.itis.dmitry_ivanov.services;

import ru.kpfu.itis.dmitry_ivanov.entity.Reservation;

import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */
public interface ReservationService {

    List<Reservation> findAll();

    void save(Reservation reservation);

    void remove(String id);

    Reservation findById(String id);

    void update(Reservation reservation);
}
