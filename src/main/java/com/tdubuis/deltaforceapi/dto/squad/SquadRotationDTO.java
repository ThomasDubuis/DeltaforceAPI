package com.tdubuis.deltaforceapi.dto.squad;

import com.tdubuis.deltaforceapi.entity.Squad.SquadRotation;

import java.util.UUID;

public record SquadRotationDTO(UUID playerId, int rotation) {

	public static SquadRotationDTO from(SquadRotation squadRotation)
	{
		return  new SquadRotationDTO(squadRotation.getPlayer().getId(),  squadRotation.getRotation());
	}
}
