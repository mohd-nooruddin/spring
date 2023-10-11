package com.spring.security.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.security.entity.UserEntity;



public class UserInforUserDetails implements UserDetails{
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	
	public UserInforUserDetails(UserEntity userEntity) {
		this.userName = userEntity.getEmail();
		this.password = userEntity.getPassword();
		this.authorities = 
				Arrays.stream(userEntity.getRoles().split(",")) //Splitting Multiple Roles by comma
				.map(SimpleGrantedAuthority :: new) //Creating New Object to give them roles
				.collect(Collectors.toList()); //Assigning every objects of SimpleGrantedAuthority to list
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
//		return false;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
