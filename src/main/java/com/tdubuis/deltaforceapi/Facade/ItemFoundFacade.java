package com.tdubuis.deltaforceapi.Facade;

import com.tdubuis.deltaforceapi.dto.ItemFoundDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadRotationDTO;
import com.tdubuis.deltaforceapi.service.ItemFoundService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ItemFoundFacade
{
	private final ItemFoundService itemFoundService;

	public ItemFoundFacade(ItemFoundService itemFoundService)
	{
		this.itemFoundService = itemFoundService;
	}

	public List<ItemFoundDTO> getAllItemFound(UUID playerId, UUID squadId, UUID redId, LocalDateTime fromDate, LocalDateTime toDate)
	{
		return itemFoundService.getAllItemFound(playerId, squadId, redId, fromDate, toDate)
				.stream().map(ItemFoundDTO::from).toList();
	}

	public List<SquadRotationDTO> redFound(UUID playerId, UUID redId, UUID foundBy, UUID giveAt,UUID squadId, Boolean useRotation)
	{
		return itemFoundService.redFound(playerId, redId, foundBy, giveAt, squadId, useRotation)
				.stream().map(SquadRotationDTO::from).toList();
	}
}
