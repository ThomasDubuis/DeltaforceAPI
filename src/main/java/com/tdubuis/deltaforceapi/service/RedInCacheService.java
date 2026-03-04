package com.tdubuis.deltaforceapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tdubuis.deltaforceapi.dto.RedItemInCacheWithLvlDTO;
import com.tdubuis.deltaforceapi.entity.RedLvlInCache;
import com.tdubuis.deltaforceapi.repository.RedInCacheRepository;

@Service
public class RedInCacheService
{
	private final RedInCacheRepository redInCacheRepository;

	public RedInCacheService(RedInCacheRepository redInCacheRepository)
	{
		this.redInCacheRepository = redInCacheRepository;
	}

	public List<RedItemInCacheWithLvlDTO> getAllRedInCache(Long playerId, Long redId, RedLvlInCache lvl)
	{
		return redInCacheRepository.findAllRedInCacheWithFilters(playerId, redId, lvl);
	}
}
