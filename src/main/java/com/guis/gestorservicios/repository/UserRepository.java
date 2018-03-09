package com.guis.gestorservicios.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guis.gestorservicios.entities.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
	
	public abstract User findByUsername(String username);
	
}
