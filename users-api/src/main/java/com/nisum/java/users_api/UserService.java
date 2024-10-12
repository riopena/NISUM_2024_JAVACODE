package com.nisum.java.users_api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nisum.java.users_api.exception.UserNotFoundException;
import com.nisum.java.users_api.jwt.JwtService;
import com.nisum.java.users_api.repository.PhonesRepository;
import com.nisum.java.users_api.repository.UsersRepository;

@Service
public class UserService {
	
	private final UsersRepository userRepo;
	private final PhonesRepository phoneRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public UserService(UsersRepository userRepo, PhonesRepository phoneRepo, JwtService jwtService, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepo = userRepo;
		this.phoneRepo = phoneRepo;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;

	}
	
	public User updateUsers(User user) {
		if (userRepo.existsById(user.getIdUsuario())) {
			User oldUser = userRepo.getById(user.getIdUsuario());

			user.setLastlogin(oldUser.getLastlogin());
			user.setCreatedDate(oldUser.getCreatedDate());
			user.setUsername(user.getName());
			user.setName(user.getName());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setToken(jwtService.getToken(user));
			user.setModified(LocalDate.now());

			List<Phone> phones = user.getPhones();

			user.setPhones(new ArrayList<Phone>());
			User savedUser = userRepo.save(user);

			phones.forEach(phone -> {
				phone.setUser(savedUser);
				phone = phoneRepo.save(phone);
				// user.getPhones().add(phone);
			});

		} else {
			throw new UserNotFoundException("No Existe el usuario que desea modificar");
		}

		return user;
	}
	
}
