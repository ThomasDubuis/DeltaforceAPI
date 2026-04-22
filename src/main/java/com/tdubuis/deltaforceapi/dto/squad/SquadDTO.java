package com.tdubuis.deltaforceapi.dto.squad;

import com.tdubuis.deltaforceapi.dto.PlayerDTO;
import com.tdubuis.deltaforceapi.entity.Squad.Squad;

import java.util.List;
import java.util.UUID;

public record SquadDTO(UUID id, String name, PlayerDTO playerId1, PlayerDTO playerId2, PlayerDTO playerId3, List<SquadRotationDTO> rotations)
{
	public static SquadDTO from(Squad squad)
	{
		List<SquadRotationDTO> squadRotationDTO = squad.getRotations().stream()
				.map(SquadRotationDTO::from)
				.toList();

		return new SquadDTO(squad.getId(), squad.getName(), PlayerDTO.from(squad.getPlayer1()), PlayerDTO.from(squad.getPlayer2()), PlayerDTO.from(squad.getPlayer3()), squadRotationDTO);
	}
}
