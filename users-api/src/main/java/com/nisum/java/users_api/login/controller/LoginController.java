package com.nisum.java.users_api.login.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nisum.java.users_api.Phone;
import com.nisum.java.users_api.User;
import com.nisum.java.users_api.UserDaoService;
import com.nisum.java.users_api.exception.InvalidEmailException;
import com.nisum.java.users_api.exception.UserNotFoundException;
import com.nisum.java.users_api.jwt.JwtService;
import com.nisum.java.users_api.repository.PhonesRepository;
import com.nisum.java.users_api.repository.UsersRepository;

@RestController
@RequestMapping("/authorization")
public class LoginController {

	private UserDaoService service;
	private UsersRepository userRepo;
	private PhonesRepository phoneRepo;
	private final JwtService jwtService;
	private final LoginService loginService;

	public LoginController(UserDaoService service, UsersRepository userRepo, PhonesRepository phoneRepo,
			JwtService jwtService, LoginService loginService) {
		super();
		this.service = service;
		this.userRepo = userRepo;
		this.phoneRepo = phoneRepo;
		this.jwtService = jwtService;
		this.loginService = loginService;

	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(loginService.login(request));

	}

	@PostMapping("/registro")
	public ResponseEntity<User> saveUsers(@Validated @RequestBody User user) {
		User re;

		try {
			re = loginService.registro(user);
		} catch (Exception e) {
			throw new InvalidEmailException("El correo ya esta registrado");
		}
		return ResponseEntity.ok(re);
	}

}
