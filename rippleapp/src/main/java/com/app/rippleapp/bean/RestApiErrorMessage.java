package com.app.rippleapp.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestApiErrorMessage {

	    private HttpStatus errorStatus;
	    private int errorCode;
	    private String errorMessage;
		private List<String> errors;
		
	    public RestApiErrorMessage(HttpStatus errorStatus, String errorMessage, String error) {
	        super();
	        this.errorStatus = errorStatus;
	        this.errorCode = errorStatus.value();
	        this.errorMessage = errorMessage;
	        this.errors = Arrays.asList(error);
	    }

		public RestApiErrorMessage(HttpStatus errorStatus, String errorMessage, List<String> errors) {
			 super();
		        this.errorStatus = errorStatus;
		        this.errorCode = errorStatus.value();
		        this.errorMessage = errorMessage;
		        this.errors = errors;
		}
	}
