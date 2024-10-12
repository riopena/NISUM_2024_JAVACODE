package com.nisum.java.users_api.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nisum.java.users_api.User;

import jakarta.validation.constraints.NotNull;
@DataJpaTest
class UsersRepositoryTest {
	@Autowired
	private UsersRepository userRepository;

	@AfterEach
    void tearDown() {
		userRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfUserFindByUsername() {
        String name = "marioPena";
        //given
        User user = new User(null,UUID.randomUUID(), name, "riopena07@gmail.com","123456",
    			LocalDate.now(), LocalDateTime.now(), LocalDate.now(), true,null);

        userRepository.save(user);
        //when

        Optional<User> expected = userRepository.findByUsername(name);
        //then

        assertThat(expected).isNotEmpty();
    }

    @Test
    void itShouldCheckIfUserNotFindByUsername() {
        String name = "marioPena";
        //given
        User user = new User(null,UUID.randomUUID(), name, "riopena07@gmail.com","123456",
    			LocalDate.now(), LocalDateTime.now(), LocalDate.now(), true,null);

        userRepository.save(user);
        //when

        Optional<User> expected = userRepository.findByUsername("juan");
        //then

        assertThat(expected).isEmpty();
    }

}
