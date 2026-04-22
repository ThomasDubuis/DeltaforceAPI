package com.tdubuis.deltaforceapi.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService
{

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private SecretKey getKey()
	{
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(UUID userId)
	{
		long now = System.currentTimeMillis();

		return Jwts.builder()
				.subject(userId.toString())
				.issuedAt(new Date(now))
				.expiration(new Date(now + expiration))
				.signWith(getKey())
				.compact();
	}

	public UUID extractUserId(String token)
	{
		String userId = Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
		return UUID.fromString(userId);

	}

	public boolean isTokenValid(String token)
	{
		try
		{
			Jwts.parser()
					.verifyWith(getKey())
					.build()
					.parseSignedClaims(token);

			return true;
		} catch (ExpiredJwtException e)
		{
			System.out.println("Expired Token");
		} catch (JwtException e)
		{
			System.out.println("Invalid Token");
		} catch (Exception e)
		{
			System.out.println("Other Exception : " + e.getMessage());
		}
		return false;
	}
}
