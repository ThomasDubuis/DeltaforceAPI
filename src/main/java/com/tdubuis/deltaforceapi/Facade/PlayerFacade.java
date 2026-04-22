package com.tdubuis.deltaforceapi.Facade;

import com.tdubuis.deltaforceapi.dto.PlayerDTO;
import com.tdubuis.deltaforceapi.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerFacade
{
	private final PlayerService playerService;

	public PlayerFacade(PlayerService playerService)
	{
		this.playerService = playerService;
	}

	public PlayerDTO getPlayerById(UUID playerId)
	{
		return PlayerDTO.from(playerService.getPlayerById(playerId));
	}
}
