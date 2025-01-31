package com.spring_data_jpa.event_manager.dtos;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record EventRecordDto(
        String description,
        Date date,
        UUID hostId,
        Set<UUID> guestIds
) {
}
