package com.tdubuis.deltaforceapi.dto.squad;

import jakarta.validation.constraints.NotBlank;

public record SquadCreationDTO(
		@NotBlank(message = "Squad name should be set")
		String name)
{
}
