package com.nisum.java.users_api;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nisum.java.users_api.exception.UserNotFoundException;
import com.nisum.java.users_api.jwt.JwtService;
import com.nisum.java.users_api.repository.PhonesRepository;
import com.nisum.java.users_api.repository.UsersRepository;

@RestController
public class UsersController {
	private UserService userService;
	private UsersRepository userRepo;
	private PhonesRepository phoneRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public UsersController(UserService userService, UsersRepository userRepo, PhonesRepository phoneRepo,
			JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userService=userService;
		this.userRepo = userRepo;
		this.phoneRepo = phoneRepo;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		List<User> listaUsuarios = userRepo.findAll();
		if (listaUsuarios.isEmpty())
			throw new UserNotFoundException("No hay Usuarios que mostrar");
		return userRepo.findAll();
	}

	@GetMapping("/users/{id}")
	public Optional<User> retrieveUser(@PathVariable Integer id) {

		Optional<User> user = userRepo.findById(id);

		if (user.isEmpty())
			throw new UserNotFoundException("No hay resultados para la busqueda del usuario idUsuario:" + id);
		return user;

	}

	@PutMapping("/users")
	public ResponseEntity<User> updateUsers(@Validated @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUsers(user));
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
		} else {
			throw new UserNotFoundException("No Existe el usuario que desea eliminar");
		}

	}

}
