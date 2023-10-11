package com.spring.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.config.UserInforUserDetails;
import com.spring.security.entity.UserEntity;
import com.spring.security.repository.UserRepository;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<UserEntity> userOptional = userRepository.findByEmail(username);
		 
		UserDetails userDetails = 
				userOptional.map(UserInforUserDetails :: new ) //Mapping userOption to UserInforUserDetails object using it's constructor which is implementing UserDetails 
				.orElseThrow(()-> new UsernameNotFoundException("User Not found "+username));
		
		return userDetails;
	}

}
