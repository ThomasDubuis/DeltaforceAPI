package com.tdubuis.deltaforceapi.Facade;

import com.tdubuis.deltaforceapi.dto.redItem.RedItemDTO;
import com.tdubuis.deltaforceapi.service.RedItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedItemFacade
{
	private final RedItemService redItemService;

	public RedItemFacade(RedItemService redItemService)
	{
		this.redItemService = redItemService;
	}

	public List<RedItemDTO> getAllRedItems()
	{
		return redItemService.getAllRedItems()
				.stream().map(RedItemDTO::from).toList();
	}
}
