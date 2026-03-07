package com.tdubuis.deltaforceapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdubuis.deltaforceapi.entity.ItemFound;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.RedInCache;
import com.tdubuis.deltaforceapi.entity.RedInCacheId;
import com.tdubuis.deltaforceapi.entity.RedItem;
import com.tdubuis.deltaforceapi.entity.RedLvlInCache;
import com.tdubuis.deltaforceapi.repository.ItemFoundRepository;
import com.tdubuis.deltaforceapi.repository.PlayerRepository;
import com.tdubuis.deltaforceapi.repository.RedInCacheRepository;
import com.tdubuis.deltaforceapi.repository.RedItemRepository;

@Service
public class ItemFoundService
{
	private final ItemFoundRepository itemFoundRepository;
	private final PlayerRepository    playerRepository;
	private final RedItemRepository    redItemRepository;
	private final RedInCacheRepository redInCacheRepository;
	private final PlayerService playerService;

	public ItemFoundService(ItemFoundRepository itemFoundRepository, PlayerRepository playerRepository, RedItemRepository redItemRepository,  RedInCacheRepository redInCacheRepository,
			PlayerService playerService) {
		this.itemFoundRepository = itemFoundRepository;
		this.playerRepository = playerRepository;
		this.redItemRepository = redItemRepository;
		this.redInCacheRepository = redInCacheRepository;
		this.playerService = playerService;
	}

	public List<ItemFound> getAllItemFound(Long playerId, Long redId, LocalDateTime fromDate, LocalDateTime toDate)
	{
		return itemFoundRepository.findAllItemFoundWithFilters(playerId, redId, fromDate, toDate);
	}

	public List<Player> redFound(Long redId, Long foundBy, Long giveAt, Boolean useRotation)
	{
		RedItem redItem = redItemRepository.findById(redId).orElseThrow(() -> new IllegalArgumentException("RedItem not found for redId=" + redId));
		Player playerFoundBy = playerRepository.findById(foundBy).orElseThrow(() -> new IllegalArgumentException("Player not found for foundBy=" + foundBy));
		Player playerGiveAt = playerRepository.findById(giveAt).orElseThrow(() -> new IllegalArgumentException("Player not found for giveAt=" + giveAt));

		RedInCacheId redInCacheId = new RedInCacheId(redId, giveAt);
		RedInCache redInCacheForGivePlayer = redInCacheRepository.findById(redInCacheId).orElse(new RedInCache(redInCacheId, redItem, playerGiveAt, RedLvlInCache.NONE));

		List<Player> players;
		if (redInCacheForGivePlayer.upgradeLvl() && useRotation) //Change le lvl
		{
			players = playerService.changeRotation(giveAt);
		}
		else
		{
			players = playerRepository.findAllByOrderByRotationAsc();
		}

		redInCacheRepository.save(redInCacheForGivePlayer);

		ItemFound itemFound = new ItemFound();
		itemFound.setItem(redItem);
		itemFound.setPlayer(playerFoundBy);
		itemFound.setFoundAt(LocalDateTime.now());
		itemFoundRepository.save(itemFound);

		return players;
	}
}
