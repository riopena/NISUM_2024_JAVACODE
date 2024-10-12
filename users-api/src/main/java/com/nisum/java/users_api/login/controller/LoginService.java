package com.nisum.java.users_api.login.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nisum.java.users_api.Phone;
import com.nisum.java.users_api.User;
import com.nisum.java.users_api.jwt.JwtService;
import com.nisum.java.users_api.repository.PhonesRepository;
import com.nisum.java.users_api.repository.UsersRepository;

@Service
public class LoginService {
	private final UsersRepository userRepo;
	private final PhonesRepository phoneRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public LoginService(UsersRepository userRepo, PhonesRepository phoneRepo, JwtService jwtService,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userRepo = userRepo;
		this.phoneRepo = phoneRepo;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;

	}

	public LoginResponse login(LoginRequest request) {
		LoginResponse lr = new LoginResponse();
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepo.findByUsername(request.getUsername().toString()).orElseThrow();
		Optional<User> userLoad = userRepo.findByUsername(request.getUsername().toString());
		User userMod;
		if (userLoad != null) {
			userMod = userLoad.get();
			userMod.setLastlogin(LocalDateTime.now());
			userRepo.save(userMod);
		}
		String token = jwtService.getToken(user);
		lr.setToken(token);
		return lr;
	}

	public User registro(User user) {
		user.setId(UUID.randomUUID());
		user.setCreatedDate(LocalDate.now());
		user.setLastlogin(LocalDateTime.now());
		user.setModified(LocalDate.now());
		user.setActive(true);
		user.setUsername(user.getName());
		user.setName(user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setToken(jwtService.getToken(user));

		List<Phone> phones = user.getPhones();

		user.setPhones(new ArrayList<Phone>());

		User savedUser = userRepo.save(user);

		phones.forEach(phone -> {
			phone.setUser(savedUser);
			phone = phoneRepo.save(phone);
		});

		return user;

	}

}
