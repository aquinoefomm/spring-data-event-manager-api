package com.spring_data_jpa.event_manager.dtos;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record HostRecordDto(String name,
                            String document,
                            Set<UUID> eventIds) {
}
