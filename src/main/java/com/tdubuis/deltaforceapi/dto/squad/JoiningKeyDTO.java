package com.tdubuis.deltaforceapi.dto.squad;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JoiningKeyDTO(
		@NotNull
		UUID joiningKey)
{
}
