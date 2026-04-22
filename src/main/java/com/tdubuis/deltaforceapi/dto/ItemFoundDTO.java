package com.tdubuis.deltaforceapi.dto;

import com.tdubuis.deltaforceapi.dto.redItem.RedItemDTO;
import com.tdubuis.deltaforceapi.entity.ItemFound;

import java.time.LocalDateTime;
import java.util.UUID;

public record ItemFoundDTO(UUID playerId, UUID squadId, RedItemDTO redItemDTO, LocalDateTime foundAt)
{
	public static ItemFoundDTO from(ItemFound itemFound)
	{
		return new ItemFoundDTO(itemFound.getPlayer().getId(), itemFound.getSquad() == null ? null : itemFound.getSquad().getId(), RedItemDTO.from(itemFound.getItem()), itemFound.getFoundAt());
	}
}
