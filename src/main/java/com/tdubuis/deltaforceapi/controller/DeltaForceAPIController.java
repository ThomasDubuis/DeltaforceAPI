package com.tdubuis.deltaforceapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tdubuis.deltaforceapi.dto.RedItemInCacheWithLvlDTO;
import com.tdubuis.deltaforceapi.entity.ItemFound;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.RedItem;
import com.tdubuis.deltaforceapi.entity.RedLvlInCache;
import com.tdubuis.deltaforceapi.service.ItemFoundService;
import com.tdubuis.deltaforceapi.service.PlayerService;
import com.tdubuis.deltaforceapi.service.RedInCacheService;
import com.tdubuis.deltaforceapi.service.RedItemService;

@RestController
@RequestMapping("/api")
public class DeltaForceAPIController
{

	private final PlayerService     playerService;
	private final RedInCacheService redInCacheService;
	private final ItemFoundService  itemFoundService;
	private final RedItemService    redItemService;

	public DeltaForceAPIController(PlayerService playerService,  RedInCacheService redInCacheService,  ItemFoundService itemFoundService,  RedItemService redItemService)
	{
		this.playerService = playerService;
		this.redInCacheService = redInCacheService;
		this.itemFoundService = itemFoundService;
		this.redItemService = redItemService;
	}

	// BASIC GETTERS

	@GetMapping("/players")
	public List<Player> getPlayers() {
		return playerService.getAllPlayers();
	}

	@GetMapping("/redItems")
	public List<RedItem> getRedItems()
	{
		return redItemService.getAllRedItems();
	}


	// GETTERS WITH FILTERS
	@GetMapping("/redInCache")
	public List<RedItemInCacheWithLvlDTO> getRedInCache(
			@RequestParam(required = false) Long playerId,
			@RequestParam(required = false) Long redId,
			@RequestParam(required = false) RedLvlInCache lvl)
	{//TODO Faire des fake data
		return redInCacheService.getAllRedInCache(playerId, redId, lvl);
	}

	@GetMapping("/itemFound")
	public List<ItemFound> getItemFound(
			@RequestParam(required = false) Long playerId,
			@RequestParam(required = false) Long redId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate
	)
	{//TODO Faire des fake data
		return itemFoundService.getAllItemFound(playerId, redId, fromDate, toDate);
	}


	// MAIN POST METHOD
	@PostMapping("/redFound")
	public List<Player> redFound(@RequestParam Long redId, @RequestParam Long foundBy, @RequestParam Long giveAt)
	{
		return itemFoundService.redFound(redId, foundBy, giveAt);
	}

}
