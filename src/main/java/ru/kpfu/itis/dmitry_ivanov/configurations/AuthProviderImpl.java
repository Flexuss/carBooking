package ru.kpfu.itis.dmitry_ivanov.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.dmitry_ivanov.entity.Role;
import ru.kpfu.itis.dmitry_ivanov.entity.User;
import ru.kpfu.itis.dmitry_ivanov.repository.RoleRepository;
import ru.kpfu.itis.dmitry_ivanov.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 22.05.2017.
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Autowired
    public AuthProviderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getRoles().get(0).getRole().equals("ROLE_ADMIN"))
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
