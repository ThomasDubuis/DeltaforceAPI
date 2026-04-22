package com.tdubuis.deltaforceapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
		@Email(message = "Invalid email")
		String email,
		@Size(min = 6, max = 50, message = "password should contain 6 to 50 char")
		String password)
{
}
