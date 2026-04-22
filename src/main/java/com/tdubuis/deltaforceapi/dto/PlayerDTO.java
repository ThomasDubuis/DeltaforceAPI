package com.tdubuis.deltaforceapi.dto;

import com.tdubuis.deltaforceapi.entity.Player;

import java.util.UUID;

public record PlayerDTO(UUID id, String email, String name)
{
	public static PlayerDTO from(Player player)
	{
		if(player == null)
			return null;
		return new PlayerDTO(player.getId(), player.getEmail(), player.getName());
	}
}
