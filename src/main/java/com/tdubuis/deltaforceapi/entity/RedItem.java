package com.tdubuis.deltaforceapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RedItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String  name;
	private String  image;
	private Integer cost;
	@Enumerated(EnumType.STRING)
	private RedItemType type;
	private boolean exclusive;
}
