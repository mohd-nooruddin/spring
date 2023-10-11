package com.spring.security.entity;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
	private String status;
    private List<String> errors;

    public ValidationErrorResponse() {
        this.status = "error";
        this.errors = new ArrayList<>();
    }

    public ValidationErrorResponse(String error) {
        this();
        this.errors.add(error);
    }

    public ValidationErrorResponse(List<String> errors) {
        this();
        this.errors.addAll(errors);
    }

	public ValidationErrorResponse(String status, List<String> errors) {
		super();
		this.status = status;
		this.errors = errors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ValidationErrorResponse [status=" + status + ", errors=" + errors + "]";
	}
    
}
