package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.exception.ApiException;
import com.tdubuis.deltaforceapi.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService
{
	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository)
	{
		this.playerRepository = playerRepository;
	}

	public List<Player> getAllPlayers()
	{
		return playerRepository.findAll();
	}

	public Player getPlayerById(UUID playerId)
	{
		return playerRepository.findById(playerId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Player not found"));
	}
}
