package org.proleesh.springbootinpractice7.service;

import org.proleesh.springbootinpractice7.dto.UserDto;
import org.proleesh.springbootinpractice7.model.ApplicationUser;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
    ApplicationUser findByUsername(String username);
}
