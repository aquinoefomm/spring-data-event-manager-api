package com.spring_data_jpa.event_manager.services;

import com.spring_data_jpa.event_manager.dtos.EventRecordDto;
import com.spring_data_jpa.event_manager.dtos.HostRecordDto;
import com.spring_data_jpa.event_manager.models.Event;
import com.spring_data_jpa.event_manager.models.Guest;
import com.spring_data_jpa.event_manager.models.Host;
import com.spring_data_jpa.event_manager.repositories.EventRepository;
import com.spring_data_jpa.event_manager.repositories.GuestRepository;
import com.spring_data_jpa.event_manager.repositories.HostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public EventService(EventRepository eventRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.eventRepository = eventRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(UUID id){
        return eventRepository.findById(id);
    }


    @Transactional
    public Event createEvent(EventRecordDto eventRecordDto){
        Host host = hostRepository.findById(eventRecordDto.hostId())
                .orElseThrow(() -> new IllegalArgumentException("Host not found"));

//        Set<Guest> guests = guestRepository.findAllById(eventRecordDto.guestIds());

        Event event = new Event();
        event.setDescription(eventRecordDto.description());
        event.setDate(eventRecordDto.date());
        event.setHost(host);
//        event.getGuests(guests);

        return eventRepository.save(event);
    }

    @Transactional
    public void deleteById(UUID id){
        eventRepository.deleteById(id);
    }

}
