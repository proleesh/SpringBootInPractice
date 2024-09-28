package org.proleesh.springbootinpractice7.service.impl;

import lombok.RequiredArgsConstructor;
import org.proleesh.springbootinpractice7.dto.UserDto;
import org.proleesh.springbootinpractice7.model.ApplicationUser;
import org.proleesh.springbootinpractice7.repository.UserRepository;
import org.proleesh.springbootinpractice7.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser createUser(UserDto userDto) {
        ApplicationUser user = new ApplicationUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
