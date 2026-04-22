package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.Facade.PlayerFacade;
import com.tdubuis.deltaforceapi.dto.PlayerDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/player")
public class PlayerController
{

	private final PlayerFacade  playerFacade;

	public PlayerController(PlayerFacade playerFacade)
	{
		this.playerFacade = playerFacade;
	}

	@GetMapping("/me")
	public PlayerDTO getMe(@AuthenticationPrincipal UUID playerId)
	{
		return playerFacade.getPlayerById(playerId);
	}
}
