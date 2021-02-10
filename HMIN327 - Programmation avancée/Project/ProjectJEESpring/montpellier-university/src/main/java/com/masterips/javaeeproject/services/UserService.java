package com.masterips.javaeeproject.services;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.masterips.javaeeproject.controllers.dto.UserRegistrationDto;
import com.masterips.javaeeproject.entities.User;

public interface UserService extends UserDetailsService {
	
	User getUser(String email);
	
	User save(UserRegistrationDto registration);

	User save(@Valid @NotNull User user);

}
