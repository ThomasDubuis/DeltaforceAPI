package com.tdubuis.deltaforceapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tdubuis.deltaforceapi.entity.RedItem;
import com.tdubuis.deltaforceapi.repository.RedItemRepository;

@Service
public class RedItemService
{
	private final RedItemRepository redItemRepository;

	public RedItemService(RedItemRepository redItemRepository)
	{
		this.redItemRepository = redItemRepository;
	}

	public List<RedItem> getAllRedItems()
	{
		return redItemRepository.findAll();
	}

}
