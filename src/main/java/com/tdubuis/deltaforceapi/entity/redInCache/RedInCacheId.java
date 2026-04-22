package com.tdubuis.deltaforceapi.entity.redInCache;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class RedInCacheId implements Serializable
{
	private UUID redId;
	private UUID playerId;
}
