package com.spring_data_jpa.event_manager.services;

import com.spring_data_jpa.event_manager.dtos.HostRecordDto;
import com.spring_data_jpa.event_manager.models.Event;
import com.spring_data_jpa.event_manager.models.Host;
import com.spring_data_jpa.event_manager.repositories.EventRepository;
import com.spring_data_jpa.event_manager.repositories.HostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HostService {

    private final HostRepository hostRepository;
    private final EventRepository eventRepository;

    @Autowired
    public HostService(HostRepository hostRepository, EventRepository eventRepository) {
        this.hostRepository = hostRepository;
        this.eventRepository = eventRepository;
    }

    public List<Host> getAllHosts(){
        return hostRepository.findAll();
    }

    public Optional<Host> getHostById(UUID id){

        return hostRepository.findById(id);
    }

    @Transactional
    public Host createHost(HostRecordDto hostRecordDto){
        Host host = new Host();
        host.setName(hostRecordDto.name());
        host.setCpf(hostRecordDto.cpf());
//        host.setEvents(eventRepository.findAllById(hostRecordDto.eventIds()).stream().collect(Collectors.toSet()));

        return hostRepository.save(host);
    }


    @Transactional
    public boolean deleteHost(UUID id){

        if (hostRepository.existsById(id)) {
            hostRepository.deleteById(id);
            return true;
        }
        return false;

    }

}
