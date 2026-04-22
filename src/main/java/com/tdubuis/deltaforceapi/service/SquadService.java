package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.dto.squad.JoiningKeyDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadCreationDTO;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.Squad.Squad;
import com.tdubuis.deltaforceapi.entity.Squad.SquadRotation;
import com.tdubuis.deltaforceapi.entity.Squad.SquadRotationId;
import com.tdubuis.deltaforceapi.exception.ApiException;
import com.tdubuis.deltaforceapi.repository.SquadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SquadService
{
	private final SquadRepository squadRepository;

	public SquadService(SquadRepository squadRepository)
	{
		this.squadRepository = squadRepository;
	}

	public List<Squad> getSquads(UUID playerId)
	{
		return squadRepository.findAllByPlayerId(playerId);
	}

	public Squad getSquadBySquadId(UUID playerId, UUID squadId)
	{
		Squad squad = squadRepository.findSquadById(squadId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Squad not found or player not in squad"));
		if (!isPlayerInSquad(squad, playerId))
		{
			throw new ApiException(HttpStatus.NOT_FOUND, "Squad not found or player not in squad");
		}
		return squad;
	}

	public Squad createSquad(UUID playerId, SquadCreationDTO squadCreation)
	{
		Squad squad = new Squad();
		squad.setName(squadCreation.name());

		Player player = new Player();
		player.setId(playerId);

		squad.setPlayer1(player);

		SquadRotation r = new SquadRotation();
		r.setId(new SquadRotationId(null, playerId));
		r.setSquad(squad);
		r.setPlayer(player);
		r.setRotation(1);

		squad.getRotations().add(r);

		return squadRepository.save(squad);
	}

	public void deleteSquad(UUID playerId, UUID squadId)
	{
		Squad squad = squadRepository.findSquadById(squadId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player"));
		if (!squad.getPlayer1().getId().equals(playerId))
		{
			throw new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player");
		}

		squadRepository.delete(squad);
	}

	public Squad updateSquad(UUID playerId, UUID squadId, SquadCreationDTO squadCreation)
	{
		Squad squad = squadRepository.findSquadById(squadId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player"));
		if (!squad.getPlayer1().getId().equals(playerId))
		{
			throw new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player");
		}
		squad.setName(squadCreation.name());

		return squadRepository.save(squad);
	}

	public JoiningKeyDTO getJoiningKey(UUID playerId, UUID squadId)
	{
		Squad squad = squadRepository.findSquadById(squadId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player"));
		if (!squad.getPlayer1().getId().equals(playerId))
		{
			throw new ApiException(HttpStatus.NOT_FOUND, "Squad not found or not owned to this player");
		}

		return new JoiningKeyDTO(squad.getJoiningKey());
	}

	public Squad joinSquad(UUID playerId, JoiningKeyDTO joiningKey)
	{
		Squad squad = squadRepository.findSquadByJoiningKey(joiningKey.joiningKey()).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Squad not found"));

		if (isPlayerInSquad(squad, playerId))
		{
			throw new ApiException(HttpStatus.CONFLICT, "Player already in this Squad");
		}

		Player player = new Player();
		player.setId(playerId);

		squad.addPlayer(player);

		return squadRepository.save(squad);
	}


	public static boolean isPlayerInSquad(Squad squad, UUID playerId)
	{
		return (squad.getPlayer1() != null && squad.getPlayer1().getId().equals(playerId))
				|| (squad.getPlayer2() != null && squad.getPlayer2().getId().equals(playerId))
				|| (squad.getPlayer3() != null && squad.getPlayer3().getId().equals(playerId));
	}

	public Squad movePlayerToEnd(Squad squad, UUID playerId)
	{
		squad.movePlayerToEnd(playerId);
		return squadRepository.save(squad);
	}
}
