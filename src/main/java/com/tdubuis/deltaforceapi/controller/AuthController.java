package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.dto.LoginRequestDTO;
import com.tdubuis.deltaforceapi.dto.RegisterRequestDTO;
import com.tdubuis.deltaforceapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public String register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
		return authService.register(registerRequestDTO);
	}

	@PostMapping("/login")
	public String login(@RequestBody @Valid LoginRequestDTO req) {
		return authService.login(req.email(), req.password());
	}
}