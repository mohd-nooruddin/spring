package com.spring.security.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.entity.UserEntity;
import com.spring.security.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public Optional<UserEntity> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public ResponseEntity<String> registerUser(UserEntity userEntity) throws Exception {
		if (findByEmail(userEntity.getEmail()).isEmpty()) {
			if(userEntity.getRoles().isEmpty()) {
				userEntity.setRoles("ROLE_USER");
			}
			try {
				logger.info("Encoding Password.");
				userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
				logger.info("Password Encode Successfully.");
				
				logger.info("Registering User.");
				userRepository.save(userEntity);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				logger.error("Arguments Cann't be Null. Please Provide correct Arguments.");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Arguments Cann't be Null. Please Provide correct Arguments.");
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("Something Went wrong while Registering User. Please Try again later");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong while Registering User. Please Try again later");
			}
			
		}else {
//			 throw new Exception("Username/Email Already Exists");
			logger.error("Username/Email Already Exists");
			return ResponseEntity.badRequest().body("Username/Email Already Exists");
		}
		
		logger.info("User Registered Succefully.");
		return ResponseEntity.ok("User Registered Successfully.!!!");
	}
}
