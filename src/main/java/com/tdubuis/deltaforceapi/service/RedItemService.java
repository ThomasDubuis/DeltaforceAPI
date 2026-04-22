package com.tdubuis.deltaforceapi.service;

import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import com.tdubuis.deltaforceapi.exception.ApiException;
import com.tdubuis.deltaforceapi.repository.RedItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

	public RedItem getRedItemById(UUID redId)
	{
		return redItemRepository.findById(redId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "RedItem not found"));
	}

}
