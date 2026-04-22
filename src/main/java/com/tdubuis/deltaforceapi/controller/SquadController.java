package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.Facade.SquadFacade;
import com.tdubuis.deltaforceapi.dto.squad.JoiningKeyDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadCreationDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/squads")
public class SquadController
{

	private final SquadFacade squadFacade;

	public SquadController(SquadFacade squadFacade)
	{
		this.squadFacade = squadFacade;
	}

	@GetMapping("")
	public List<SquadDTO> getSquads(@AuthenticationPrincipal UUID playerId)
	{
		return squadFacade.getSquads(playerId);
	}

	@GetMapping("/{squadId}")
	public SquadDTO getSquadById(@AuthenticationPrincipal UUID playerId, @PathVariable UUID squadId)
	{
		return squadFacade.getSquadBySquadId(playerId, squadId);
	}
	@PostMapping("")
	public SquadDTO createSquad(@AuthenticationPrincipal UUID playerId, @RequestBody @Valid SquadCreationDTO squadCreation)
	{
		return squadFacade.createSquad(playerId, squadCreation);
	}

	@DeleteMapping("/{squadId}")
	public void deleteSquad(@AuthenticationPrincipal UUID playerId, @PathVariable UUID squadId)
	{
		squadFacade.deleteSquad(playerId, squadId);
	}

	@PutMapping("/{squadId}")
	public SquadDTO updateSquad(@AuthenticationPrincipal UUID playerId, @PathVariable UUID squadId, @RequestBody @Valid SquadCreationDTO squadCreation)
	{
		return squadFacade.updateSquad(playerId, squadId, squadCreation);
	}

	@GetMapping("/{squadId}/joiningKey")
	public JoiningKeyDTO getJoiningKey(@AuthenticationPrincipal UUID playerId, @PathVariable UUID squadId)
	{
		return squadFacade.getJoiningKey(playerId, squadId);
	}

	@PostMapping("/joinSquad")
	public SquadDTO joinSquad(@AuthenticationPrincipal UUID playerId,  @RequestBody @Valid JoiningKeyDTO joiningKey)
	{
		return squadFacade.joinSquad(playerId, joiningKey);
	}
}
