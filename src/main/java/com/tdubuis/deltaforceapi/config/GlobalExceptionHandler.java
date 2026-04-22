package com.tdubuis.deltaforceapi.config;

import com.tdubuis.deltaforceapi.exception.ApiException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

		String message = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(err -> err.getField() + " : " + err.getDefaultMessage())
				.findFirst()
				.orElse("Validation error");

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now()));
	}
	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ApiError> handleTypeMismatch(TypeMismatchException ex)
	{
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(HttpStatus.BAD_REQUEST.value(), "Type error for parameter : " + ex.getPropertyName(), LocalDateTime.now()));
	}

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiError> handleApiException(ApiException ex)
	{
		return ResponseEntity
				.status(ex.getStatus())
				.body(new ApiError(ex.getStatus().value(), ex.getMessage(), LocalDateTime.now()));
	}

	// fallback global
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex)
	{
		System.out.println("ERROR NOT CACHED: " + ex.getMessage());
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), LocalDateTime.now()));
	}
}