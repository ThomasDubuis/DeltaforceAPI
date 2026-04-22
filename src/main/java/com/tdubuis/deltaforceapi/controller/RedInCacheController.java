package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.Facade.RedInCacheFacade;
import com.tdubuis.deltaforceapi.dto.redItem.RedItemInCacheWithLvlDTO;
import com.tdubuis.deltaforceapi.dto.redItem.UpdateRedItem;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/redInCache")
public class RedInCacheController
{
	private final RedInCacheFacade redInCacheFacade;

	public RedInCacheController(RedInCacheFacade redInCacheFacade)
	{
		this.redInCacheFacade = redInCacheFacade;
	}

	// GETTERS WITH FILTERS
	@GetMapping("")
	public List<RedItemInCacheWithLvlDTO> getRedInCache(
			@AuthenticationPrincipal UUID playerId,
			@RequestParam(required = false) UUID squadId,
			@RequestParam(required = false) UUID redId,
			@RequestParam(required = false) RedLvlInCache lvl)
	{
		return redInCacheFacade.getAllRedInCache(playerId, squadId, redId, lvl);
	}

	@PutMapping("/{redId}")
	public RedItemInCacheWithLvlDTO updateRedInCache(@AuthenticationPrincipal UUID playerId, @PathVariable UUID redId, @RequestBody @Valid UpdateRedItem updateRedItem)
	{
		return redInCacheFacade.updateRedInCache(playerId, redId, updateRedItem);
	}


}
