package org.proleesh.springbootinpractice7.service.impl;

import lombok.RequiredArgsConstructor;
import org.proleesh.springbootinpractice7.model.ApplicationUser;
import org.proleesh.springbootinpractice7.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User with username " + username + " does not exists");
        }
        UserDetails userDetails =
                User.withUsername(username)
                        .password(user.getPassword())
                        .roles("USER")
                        .disabled(false)
                        .build();
        return userDetails;
    }
}
