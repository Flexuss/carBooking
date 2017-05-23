package ru.kpfu.itis.dmitry_ivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.dmitry_ivanov.entity.User;

/**
 * Created by Dmitry on 20.05.2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}
