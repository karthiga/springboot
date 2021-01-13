package com.app.rippleapp.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class RestApiErrorMessage {

	    private HttpStatus errorStatus;
	    private int errorCode;
	    private String errorMessage;
		private List<String> errors;
		
	    public HttpStatus getErrorStatus() {
			return errorStatus;
		}

		public void setErrorStatus(HttpStatus errorStatus) {
			this.errorStatus = errorStatus;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public List<String> getErrors() {
			return errors;
		}

		public void setErrors(List<String> errors) {
			this.errors = errors;
		}

		public RestApiErrorMessage(HttpStatus errorStatus, String message, List<String> errors) {
	        super();
	        this.errorStatus = errorStatus;
	        this.errorCode = errorStatus.value();
	        this.errorMessage = message;
	        this.errors = errors;
	    }

	    public RestApiErrorMessage(HttpStatus errorStatus, String errorMessage, String error) {
	        super();
	        this.errorStatus = errorStatus;
	        this.errorCode = errorStatus.value();
	        this.errorMessage = errorMessage;
	        errors = Arrays.asList(error);
	    }
	}
