package com.tdubuis.deltaforceapi.Facade;

import com.tdubuis.deltaforceapi.dto.redItem.RedItemInCacheWithLvlDTO;
import com.tdubuis.deltaforceapi.dto.redItem.UpdateRedItem;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import com.tdubuis.deltaforceapi.service.RedInCacheService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RedInCacheFacade
{
	private final RedInCacheService redInCacheService;

	public RedInCacheFacade(RedInCacheService redInCacheService)
	{
		this.redInCacheService = redInCacheService;
	}

	public List<RedItemInCacheWithLvlDTO> getAllRedInCache(UUID playerId, UUID squadId, UUID redId, RedLvlInCache lvl)
	{
		return redInCacheService.getAllRedInCache(playerId, squadId, redId, lvl)
				.stream().map(RedItemInCacheWithLvlDTO::from).toList();
	}

	public RedItemInCacheWithLvlDTO updateRedInCache(UUID playerId, UUID redId, UpdateRedItem updateRedItem)
	{
		return RedItemInCacheWithLvlDTO.from(redInCacheService.updateRedInCache(playerId, redId, updateRedItem));
	}
}
