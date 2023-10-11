package com.spring.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	public Optional<UserEntity> findByEmail(String email);
	

}