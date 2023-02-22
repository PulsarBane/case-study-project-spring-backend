package com.revature.clp.ecommerce.project.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// this is the handler that we have overridden and it handles the exception
		// which occurs for violation of the spring validations
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			Map<String, String> errors = new HashMap<String, String>(); // this hashmap will hold all the validation error
																		// messages

			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});

			// now the hashmap errors has all the field names and it's corresponding error
			// messages
			// so we have to return the hashmap as the response
			// but return the hashmap by enclosing it in a response entity object
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
		}

		// go ahead and handle other exceptions
		// incase of handling custom exception create handler method of your own and
		// annotate them with @ExceptionHandler

	
}