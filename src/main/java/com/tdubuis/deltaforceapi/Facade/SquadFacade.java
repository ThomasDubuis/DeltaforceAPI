package com.tdubuis.deltaforceapi.Facade;

import com.tdubuis.deltaforceapi.dto.squad.JoiningKeyDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadCreationDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadDTO;
import com.tdubuis.deltaforceapi.service.SquadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SquadFacade
{

	private final SquadService squadService;

	public SquadFacade(SquadService squadService)
	{
		this.squadService = squadService;
	}

	public List<SquadDTO> getSquads(UUID playerId)
	{
		return squadService.getSquads(playerId)
				.stream().map(SquadDTO::from).toList();
	}

	public SquadDTO getSquadBySquadId(UUID playerId, UUID squadId)
	{
		return SquadDTO.from(squadService.getSquadBySquadId(playerId, squadId));
	}

	public SquadDTO createSquad(UUID playerId, SquadCreationDTO squadCreation)
	{
		return SquadDTO.from(squadService.createSquad(playerId, squadCreation));
	}

	public void deleteSquad(UUID playerId, UUID squadId)
	{
		squadService.deleteSquad(playerId, squadId);
	}

	public SquadDTO updateSquad(UUID playerId,UUID squadId, SquadCreationDTO squadCreation)
	{
		return  SquadDTO.from(squadService.updateSquad(playerId, squadId, squadCreation));
	}

	public JoiningKeyDTO getJoiningKey(UUID playerId, UUID squadId)
	{
		return squadService.getJoiningKey(playerId, squadId);
	}

	public SquadDTO joinSquad(UUID playerId, JoiningKeyDTO joiningKey)
	{
		return  SquadDTO.from(squadService.joinSquad(playerId, joiningKey));
	}
}
