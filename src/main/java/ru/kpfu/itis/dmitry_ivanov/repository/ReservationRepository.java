package ru.kpfu.itis.dmitry_ivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.dmitry_ivanov.entity.Reservation;

/**
 * Created by Dmitry on 22.05.2017.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
