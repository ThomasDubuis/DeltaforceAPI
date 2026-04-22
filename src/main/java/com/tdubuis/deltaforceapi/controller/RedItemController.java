package com.tdubuis.deltaforceapi.controller;

import com.tdubuis.deltaforceapi.Facade.RedItemFacade;
import com.tdubuis.deltaforceapi.dto.redItem.RedItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/redItems")
public class RedItemController
{
	private final RedItemFacade redItemFacade;

	public RedItemController(RedItemFacade redItemFacade)
	{
		this.redItemFacade = redItemFacade;
	}

	@GetMapping("")
	public List<RedItemDTO> getRedItems()
	{
		return redItemFacade.getAllRedItems();
	}
}
