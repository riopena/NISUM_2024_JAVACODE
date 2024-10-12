package com.nisum.java.users_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.nisum.java.users_api.Phone;

@Component
public interface PhonesRepository extends JpaRepository<Phone, Integer>{

}
