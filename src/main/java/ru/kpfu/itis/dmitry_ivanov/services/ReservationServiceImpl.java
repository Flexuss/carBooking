package ru.kpfu.itis.dmitry_ivanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitry_ivanov.entity.Reservation;
import ru.kpfu.itis.dmitry_ivanov.repository.ReservationRepository;

import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void remove(String id) {
        reservationRepository.delete(Long.valueOf(id));
    }

    @Override
    public Reservation findById(String id) {
        return reservationRepository.findOne(Long.valueOf(id));
    }

    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }


}
