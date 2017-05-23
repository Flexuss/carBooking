package ru.kpfu.itis.dmitry_ivanov.services;

import ru.kpfu.itis.dmitry_ivanov.entity.User;

/**
 * Created by Dmitry on 20.05.2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
