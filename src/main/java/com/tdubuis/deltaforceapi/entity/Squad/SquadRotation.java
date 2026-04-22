package com.tdubuis.deltaforceapi.entity.Squad;

import com.tdubuis.deltaforceapi.entity.Player;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SquadRotation
{
	@EmbeddedId
	private SquadRotationId id;

	@MapsId("squadId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Squad squad;

	@MapsId("playerId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Player player;

	@Column(nullable = false)
	private int rotation;
}
