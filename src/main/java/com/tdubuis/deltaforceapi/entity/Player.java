package com.tdubuis.deltaforceapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Player
{
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String email;
	@JsonIgnore
	private String password;
	private String name;
}
