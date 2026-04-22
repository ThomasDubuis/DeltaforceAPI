package com.tdubuis.deltaforceapi.config;

import java.time.LocalDateTime;

public record ApiError(
		int status,
		String message,
		LocalDateTime timestamp
) {}
