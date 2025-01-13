package com.spring_data_jpa.event_manager.controllers;

import com.spring_data_jpa.event_manager.dtos.EventRecordDto;
import com.spring_data_jpa.event_manager.models.Event;
import com.spring_data_jpa.event_manager.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id){
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventRecordDto eventRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRecordDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        eventService.deleteById(id);
    }

}
