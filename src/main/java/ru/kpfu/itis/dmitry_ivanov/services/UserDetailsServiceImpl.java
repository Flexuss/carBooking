package ru.kpfu.itis.dmitry_ivanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dmitry_ivanov.entity.Role;
import ru.kpfu.itis.dmitry_ivanov.entity.User;
import ru.kpfu.itis.dmitry_ivanov.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry on 21.05.2017.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role : user.getRoles()){
            grantedAuthorities.add((new SimpleGrantedAuthority(role.getRole())));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
