package com.tdubuis.deltaforceapi.entity;

public enum RedLvlInCache
{
	NONE,
	LVL0,
	LVL1,
	LVL1_1,
	LVL1_2,
	MAXED;

	/**
	 * Retourne le niveau suivant.
	 * Si le niveau est MAXED, renvoie MAXED lui-même.
	 *
	 * @return le niveau suivant
	 */
	public RedLvlInCache next() {
		return switch (this) {
			case NONE -> LVL0;
			case LVL0 -> LVL1;
			case LVL1 -> LVL1_1;
			case LVL1_1 -> LVL1_2;
			case LVL1_2 -> MAXED;
			case MAXED -> MAXED;
		};
	}
}
