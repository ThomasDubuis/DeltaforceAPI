package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.Facade.ItemFoundFacade;
import com.tdubuis.deltaforceapi.dto.ItemFoundDTO;
import com.tdubuis.deltaforceapi.dto.squad.SquadRotationDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/itemFound")
public class ItemFoundController
{
	private final ItemFoundFacade itemFoundFacade;

	public ItemFoundController(ItemFoundFacade itemFoundFacade)
	{
		this.itemFoundFacade = itemFoundFacade;
	}

	@GetMapping("")
	public List<ItemFoundDTO> getItemFound(
			@AuthenticationPrincipal UUID playerId,
			@RequestParam(required = false) UUID squadId,
			@RequestParam(required = false) UUID redId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate
	)
	{
		return itemFoundFacade.getAllItemFound(playerId, squadId, redId, fromDate, toDate);
	}

	@PostMapping("/redFound")
	public List<SquadRotationDTO> redFound(@AuthenticationPrincipal UUID playerId, @RequestParam UUID redId, @RequestParam UUID foundBy, @RequestParam UUID giveAt, @RequestParam(required = false) UUID squadId, @RequestParam(required = false, defaultValue = "true") Boolean useRotation)
	{
		return itemFoundFacade.redFound(playerId, redId, foundBy, giveAt, squadId, useRotation);
	}
}
