package com.tdubuis.deltaforceapi.entity;

import com.tdubuis.deltaforceapi.entity.Squad.Squad;
import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class ItemFound
{
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Player player;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn()
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Squad squad; //Can be null if player play solo or if squad is deleted

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private RedItem item;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime foundAt;

}
