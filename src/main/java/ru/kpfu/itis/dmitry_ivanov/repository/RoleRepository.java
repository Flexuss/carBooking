package ru.kpfu.itis.dmitry_ivanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.dmitry_ivanov.entity.Role;

/**
 * Created by Dmitry on 20.05.2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role getByRole(String role);
}
