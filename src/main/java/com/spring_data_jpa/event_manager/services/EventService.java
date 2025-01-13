package com.spring_data_jpa.event_manager.services;

import com.spring_data_jpa.event_manager.dtos.HostRecordDto;
import com.spring_data_jpa.event_manager.models.Event;
import com.spring_data_jpa.event_manager.models.Host;
import com.spring_data_jpa.event_manager.repositories.EventRepository;
import com.spring_data_jpa.event_manager.repositories.GuestRepository;
import com.spring_data_jpa.event_manager.repositories.HostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


//    @Transactional
//    public Event createHost(EventRecordDto eventRecordDto){
//        Host host = new Host();
//        host.setName(hostRecordDto.name());
//        host.setDocument(hostRecordDto.document());
////        host.setEvents(eventRepository.findAllById(hostRecordDto.eventIds()).stream().collect(Collectors.toSet()));
//
//        return hostRepository.save(host);
//    }

    @Transactional
    public void deleteById(UUID id){
        eventRepository.deleteById(id);
    }

}
