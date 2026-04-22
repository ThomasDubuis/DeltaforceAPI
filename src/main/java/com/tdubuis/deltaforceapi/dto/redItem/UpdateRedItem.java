package com.tdubuis.deltaforceapi.dto.redItem;

import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import jakarta.validation.constraints.NotNull;

public record UpdateRedItem(
		@NotNull
		RedLvlInCache level)
{}
