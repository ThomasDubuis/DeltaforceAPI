package com.tdubuis.deltaforceapi.dto.redItem;

import com.tdubuis.deltaforceapi.entity.redInCache.RedInCache;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;

import java.util.UUID;

public record RedItemInCacheWithLvlDTO(UUID playerId, RedItemDTO redItem, RedLvlInCache redLvlInCache)
{
	public static RedItemInCacheWithLvlDTO from(RedInCache redInCache)
	{
		return new RedItemInCacheWithLvlDTO(redInCache.getPlayer().getId(), RedItemDTO.from(redInCache.getRedItem()), redInCache.getLvl());
	}
}
