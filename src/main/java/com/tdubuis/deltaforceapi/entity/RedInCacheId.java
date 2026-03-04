package com.tdubuis.deltaforceapi.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class RedInCacheId implements Serializable
{
	private Long redId;
	private Long playerId;
}
