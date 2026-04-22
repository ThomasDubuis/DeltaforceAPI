package com.tdubuis.deltaforceapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
		@Email(message = "Invalid email")
		String email,
		@Size(min = 6, max = 50, message = "password should contain 6 to 50 char")
		String password,
		@Size(min = 6, max = 50, message = "confirmPassword should contain 6 to 50 char")
		String confirmPassword,
		@NotBlank(message = "name should be set")
		String name)
{
}
