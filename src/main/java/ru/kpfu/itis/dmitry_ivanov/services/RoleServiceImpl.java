package ru.kpfu.itis.dmitry_ivanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitry_ivanov.repository.RoleRepository;

import java.util.Queue;

/**
 * Created by Dmitry on 20.05.2017.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

}
