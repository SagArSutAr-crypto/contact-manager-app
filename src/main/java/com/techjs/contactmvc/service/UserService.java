package com.techjs.contactmvc.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techjs.contactmvc.model.User;
import com.techjs.contactmvc.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int createUser(User user) {
		if (validateUser(user) != 404) {
			return 202;
		}
		User save = userRepository.save(user);
		if (Objects.isNull(save))
			return 400;
		return 201;
	}

	public int validateUser(User user) {
		User savedUser = userRepository.findByEmail(user.getEmail());
		if (Objects.isNull(savedUser))
			return 404;
		return 200;
	}

	public int login(User user) {
		if (validateUser(user) == 404)
			return 404;
		User savedUser = userRepository.findByEmail(user.getEmail());
		if (savedUser.getEmail().equalsIgnoreCase(user.getEmail())
				&& savedUser.getPassword().equalsIgnoreCase(user.getPassword()))
			return 200;
		return 404;
	}
}
