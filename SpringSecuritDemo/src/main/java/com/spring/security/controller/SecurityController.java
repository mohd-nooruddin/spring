package com.spring.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Sockaddr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.UserEntity;
import com.spring.security.entity.ValidationErrorResponse;
import com.spring.security.service.UserService;

import jakarta.validation.Valid;

@RestController
public class SecurityController {
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@GetMapping(value = "/home")
	public String home() {
		return "Successfully retuned in home ";
	}
	
	
	@GetMapping(value = "/welcome")
	public String welcome() {
		return "Welcome to My portal with out login ";
	}
	
	@GetMapping(value = "/")
	public String greetings() {
		return "Welcome!!!. Good to See You again !!! ";
	}
	
	@GetMapping(value = "/user")
//	Possible to do this from here but if you will not be user then you will get complete error log which is not good
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userMessage() {
		return "Opened Only for user's not for admin";
	}
	
	@GetMapping(value = "/admin")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminMessage() {
		return "Only for Admin's not for User's";
	}
	
	@GetMapping(value = "/aduse")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminAnduserMessage() {
		return "It's for both Admin's and User's";
	}
	
	@GetMapping(value = "/register")
//	Did GetMapping because if I was using PostMapping then the request wasn't working.
	public ResponseEntity<String> register(@Valid @RequestBody UserEntity userEntity) throws Exception{
		return userService.registerUser(userEntity);
	}
	
	@PostMapping(value = "/new")
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserEntity userEntity) throws Exception {
		logger.warn("Inside /new");
		return userService.registerUser(userEntity);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ValidationErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
            
            logger.error(fieldName + ": " + errorMessage);
        });
        return new ValidationErrorResponse(errors);
    }
}
