package com.tdubuis.deltaforceapi.entity.redItem;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class RedItem
{
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String  name;
	private String  image;
	@Enumerated(EnumType.STRING)
	private RedItemType type;
	private boolean exclusive;
}
