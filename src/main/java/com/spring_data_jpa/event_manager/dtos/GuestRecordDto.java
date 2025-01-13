package com.spring_data_jpa.event_manager.dtos;

import java.util.Set;
import java.util.UUID;

public record GuestRecordDto(String name,
                             String cpf,
                             Set<UUID> eventIds) {
}


