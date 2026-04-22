package com.tdubuis.deltaforceapi.entity.Squad;

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
public class SquadRotationId implements Serializable
{
	private UUID squadId;
	private UUID playerId;
}
