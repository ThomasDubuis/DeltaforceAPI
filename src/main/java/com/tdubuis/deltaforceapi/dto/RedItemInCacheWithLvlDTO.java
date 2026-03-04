package com.tdubuis.deltaforceapi.dto;

import com.tdubuis.deltaforceapi.entity.RedItem;
import com.tdubuis.deltaforceapi.entity.RedLvlInCache;

public record RedItemInCacheWithLvlDTO(Long playerId, RedItem redItem, RedLvlInCache redLvlInCache)
{
}
