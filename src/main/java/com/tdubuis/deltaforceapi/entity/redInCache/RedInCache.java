package com.tdubuis.deltaforceapi.entity.redInCache;

import com.tdubuis.deltaforceapi.entity.Player;
import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class RedInCache
{
	@EmbeddedId
	private RedInCacheId id;

	@MapsId("redId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private RedItem redItem;

	@MapsId("playerId")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn
	private Player player;

	@Enumerated(EnumType.STRING)
	private RedLvlInCache lvl;

	/**
	 * Augmente le niveau de ce RedInCache.
	 *
	 * <p>Le niveau passe au suivant selon l'ordre :
	 * NONE → LVL0 → LVL1 → LVL1_1 → LVL1_2 → MAXED.</p>
	 *
	 * <p>Si le niveau est déjà MAXED, il reste inchangé.</p>
	 *
	 * @return {@code true} si le niveau a augmenté, {@code false} si il était déjà au maximum
	 */
	public boolean upgradeLvl() {
		RedLvlInCache oldLvl = this.lvl;
		this.lvl = redItem.isExclusive() ? RedLvlInCache.MAXED : this.lvl.next(); //If Exclusive item is set to MAXED
		return oldLvl != this.lvl;
	}
}
