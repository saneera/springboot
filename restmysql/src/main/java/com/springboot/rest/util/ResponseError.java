package com.springboot.rest.util;

public class ResponseError {
	 private String errorMessage;
	 
	    public ResponseError(String errorMessage){
	        this.errorMessage = errorMessage;
	    }
	 
	    public String getErrorMessage() {
	        return errorMessage;
	    }
}
