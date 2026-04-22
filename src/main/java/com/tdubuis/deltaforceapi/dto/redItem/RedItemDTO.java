package com.tdubuis.deltaforceapi.dto.redItem;

import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import com.tdubuis.deltaforceapi.entity.redItem.RedItemType;

import java.util.UUID;

public record RedItemDTO(UUID id, String  name, String  image, RedItemType type, boolean exclusive)
{
	public static RedItemDTO from(RedItem entity) {
		return new RedItemDTO(entity.getId(), entity.getName(), entity.getImage(), entity.getType(), entity.isExclusive());
	}
}
