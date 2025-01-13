package com.spring_data_jpa.event_manager.services;

import com.spring_data_jpa.event_manager.dtos.GuestRecordDto;
import com.spring_data_jpa.event_manager.models.Guest;
import com.spring_data_jpa.event_manager.repositories.EventRepository;
import com.spring_data_jpa.event_manager.repositories.GuestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuestService {
    private final GuestRepository guestRepository;
    private final EventRepository eventRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository, EventRepository eventRepository) {
        this.guestRepository = guestRepository;
        this.eventRepository = eventRepository;
    }

    public List<Guest> getAllGuests(){
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuestById(UUID id){
        return guestRepository.findById(id);
    }

    @Transactional
    public Guest createGuest(GuestRecordDto guestRecordDto){
//        Set<Event> events = (Set<Event>) eventRepository.findAllById(guestRecordDto.eventIds());

        Guest guest = new Guest();
        guest.setCpf(guestRecordDto.cpf());
        guest.setName(guestRecordDto.name());
//        guest.setEvents(events);

        return guestRepository.save(guest);
    }

    @Transactional
    public void deleteById(UUID id){
        guestRepository.deleteById(id);
    }

}
