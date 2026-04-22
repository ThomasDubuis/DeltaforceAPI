package com.tdubuis.deltaforceapi.config;

import com.tdubuis.deltaforceapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

public class JwtAuthFilter extends OncePerRequestFilter
{

	private final JwtService jwtService;

	public JwtAuthFilter(JwtService jwtService)
	{
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain chain)
			throws ServletException, IOException
	{

		String header = request.getHeader("Authorization");

		//Do not try to resolve token if no token or if is authService
		if (header == null || !header.startsWith("Bearer ") || request.getRequestURI().startsWith("/api/auth"))
		{
			chain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);

		if (!jwtService.isTokenValid(token))
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		UUID playerId = jwtService.extractUserId(token);

		UsernamePasswordAuthenticationToken auth =
				new UsernamePasswordAuthenticationToken(
						playerId,
						null,
						Collections.emptyList()
				);

		SecurityContextHolder.getContext().setAuthentication(auth);

		chain.doFilter(request, response);
	}
}