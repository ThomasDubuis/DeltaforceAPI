package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.dto.redItem.UpdateRedItem;
import com.tdubuis.deltaforceapi.entity.ItemFound;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.Squad.Squad;
import com.tdubuis.deltaforceapi.entity.Squad.SquadRotation;
import com.tdubuis.deltaforceapi.entity.redInCache.RedInCache;
import com.tdubuis.deltaforceapi.entity.redInCache.RedInCacheId;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import com.tdubuis.deltaforceapi.exception.ApiException;
import com.tdubuis.deltaforceapi.repository.ItemFoundRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemFoundService
{
	private final ItemFoundRepository itemFoundRepository;
	private final SquadService squadService;
	private final PlayerService playerService;
	private final RedItemService redItemService;
	private final RedInCacheService redInCacheService;

	public ItemFoundService(ItemFoundRepository itemFoundRepository, SquadService squadService, PlayerService playerService, RedItemService redItemService, RedInCacheService redInCacheService)
	{
		this.itemFoundRepository = itemFoundRepository;
		this.squadService = squadService;
		this.playerService = playerService;
		this.redItemService = redItemService;
		this.redInCacheService = redInCacheService;
	}

	public List<ItemFound> getAllItemFound(UUID playerId, UUID squadId, UUID redId, LocalDateTime fromDate, LocalDateTime toDate)
	{
		if (squadId != null)
		{
			Squad squad = squadService.getSquadBySquadId(playerId, squadId); //If squad not exist or player not in squad throw exception
			return itemFoundRepository.findAllItemFoundWithFilters(null, redId, squad.getId(), fromDate, toDate);
		}

		return itemFoundRepository.findAllItemFoundWithFilters(playerId, redId, null, fromDate, toDate);
	}

	public List<SquadRotation> redFound(UUID playerId, UUID redId, UUID foundBy, UUID giveAt, UUID squadId, Boolean useRotation)
	{
		RedItem redItem = redItemService.getRedItemById(redId);
		Player playerFoundBy = playerService.getPlayerById(foundBy);
		Player playerGiveAt = playerService.getPlayerById(giveAt);
		Squad squad = squadId == null ? null : squadService.getSquadBySquadId(playerId, squadId);
		if (squad == null)
		{
			if (!playerFoundBy.getId().equals(playerId) || !playerGiveAt.getId().equals(playerId))
			{
				throw new ApiException(HttpStatus.BAD_REQUEST, "When squadId is null foundBy and giveAt should be the connected player");
			}
		}
		else
		{
			if (!SquadService.isPlayerInSquad(squad, foundBy) || !SquadService.isPlayerInSquad(squad, giveAt))
			{
				throw new ApiException(HttpStatus.BAD_REQUEST, "Player foundBy or giveAt is not in Squad");
			}
		}

		//Save item found
		ItemFound itemFound = new ItemFound();
		itemFound.setItem(redItem);
		itemFound.setPlayer(playerFoundBy);
		itemFound.setSquad(squad);
		itemFound.setFoundAt(LocalDateTime.now());
		itemFoundRepository.save(itemFound);


		RedInCacheId redInCacheId = new RedInCacheId(redId, giveAt);
		RedInCache redInCacheForGivePlayer = redInCacheService.getRedInCacheById(giveAt, redId).orElse(new RedInCache(redInCacheId, redItem, playerGiveAt, RedLvlInCache.NONE));
		boolean isUpgrade = redInCacheForGivePlayer.upgradeLvl();

		if (isUpgrade)
		{
			redInCacheService.updateRedInCache(giveAt, redId, new UpdateRedItem(redInCacheForGivePlayer.getLvl()));
			if (squad != null && useRotation)
			{
				return squadService.movePlayerToEnd(squad, playerId).getRotations();
			}
		}

		return squad == null ? new ArrayList<>() : squad.getRotations();
	}
}
