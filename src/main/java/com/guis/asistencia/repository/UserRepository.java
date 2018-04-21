package com.guis.asistencia.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guis.asistencia.entities.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable> {
	
	public abstract User findByUsername(String username);
	
}
