package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.dto.RegisterRequestDTO;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.exception.ApiException;
import com.tdubuis.deltaforceapi.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final PlayerRepository playerRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	public AuthService(PlayerRepository playerRepository,
					   JwtService jwtService,
					   PasswordEncoder passwordEncoder) {
		this.playerRepository = playerRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
	}

	public String register(RegisterRequestDTO registerRequestDTO) {

		if (!registerRequestDTO.password().equals(registerRequestDTO.confirmPassword()))
		{
			throw new ApiException(HttpStatus.BAD_REQUEST, "Password not match");
		}
		if (playerRepository.findPlayerByEmail(registerRequestDTO.email()).isPresent())
		{
			throw new ApiException(HttpStatus.CONFLICT, "Player with this email already exists");
		}


		Player player = new Player();
		player.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
		player.setEmail(registerRequestDTO.email());
		player.setName(registerRequestDTO.name());
		playerRepository.save(player);
		return jwtService.generateToken(player.getId());
	}

	public String login(String email, String password) {
		Player player = playerRepository.findPlayerByEmail(email)
				.orElseThrow(() -> new ApiException(HttpStatus.FORBIDDEN, "Bad credentials"));

		if (!passwordEncoder.matches(password, player.getPassword())) {
			throw new ApiException(HttpStatus.FORBIDDEN, "Bad credentials");
		}

		return jwtService.generateToken(player.getId());
	}
}