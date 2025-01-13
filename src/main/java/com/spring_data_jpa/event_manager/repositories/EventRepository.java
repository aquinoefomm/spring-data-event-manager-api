package com.spring_data_jpa.event_manager.repositories;

import com.spring_data_jpa.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
