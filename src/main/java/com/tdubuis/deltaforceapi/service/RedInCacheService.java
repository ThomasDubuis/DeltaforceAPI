package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.dto.redItem.UpdateRedItem;
import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.Squad.Squad;
import com.tdubuis.deltaforceapi.entity.redInCache.RedInCache;
import com.tdubuis.deltaforceapi.entity.redInCache.RedInCacheId;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import com.tdubuis.deltaforceapi.repository.RedInCacheRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RedInCacheService
{
	private final RedInCacheRepository redInCacheRepository;
	private final SquadService squadService;
	private final RedItemService redItemService;
	private final PlayerService playerService;

	public RedInCacheService(RedInCacheRepository redInCacheRepository, SquadService squadService, RedItemService redItemService, PlayerService playerService)
	{
		this.redInCacheRepository = redInCacheRepository;
		this.squadService = squadService;
		this.redItemService = redItemService;
		this.playerService = playerService;
	}

	public List<RedInCache> getAllRedInCache(UUID playerId, UUID squadId, UUID redId, RedLvlInCache lvl)
	{
		if (squadId != null)
		{
			Squad squad = squadService.getSquadBySquadId(playerId, squadId);
			List<RedInCache> redInCacheWithLvl = new ArrayList<>();
			if (squad.getPlayer1() != null)
				redInCacheWithLvl.addAll(redInCacheRepository.findAllRedInCacheWithFilters(squad.getPlayer1().getId(), redId, lvl));
			if (squad.getPlayer2() != null)
				redInCacheWithLvl.addAll(redInCacheRepository.findAllRedInCacheWithFilters(squad.getPlayer2().getId(), redId, lvl));
			if (squad.getPlayer3() != null)
				redInCacheWithLvl.addAll(redInCacheRepository.findAllRedInCacheWithFilters(squad.getPlayer3().getId(), redId, lvl));
			return redInCacheWithLvl;
		}

		return redInCacheRepository.findAllRedInCacheWithFilters(playerId, redId, lvl);
	}

	public RedInCache updateRedInCache(UUID playerId, UUID redId, UpdateRedItem updateRedItem)
	{
		RedItem redItem = redItemService.getRedItemById(redId);
		Player player = playerService.getPlayerById(playerId);

		RedInCacheId redInCacheId = new RedInCacheId(redId, playerId);
		RedInCache redInCache = redInCacheRepository.findById(redInCacheId).orElse(new RedInCache(redInCacheId, redItem, player, RedLvlInCache.NONE));
		redInCache.setLvl(updateRedItem.level());
		return redInCacheRepository.save(redInCache);
	}

	public Optional<RedInCache> getRedInCacheById(UUID playerId, UUID redId)
	{
		RedInCacheId redInCacheId = new RedInCacheId(redId, playerId);
		return redInCacheRepository.findById(redInCacheId);
	}
}
