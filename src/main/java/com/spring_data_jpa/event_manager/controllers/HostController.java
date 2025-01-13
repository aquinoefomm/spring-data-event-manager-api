package com.spring_data_jpa.event_manager.controllers;

import com.spring_data_jpa.event_manager.dtos.HostRecordDto;
import com.spring_data_jpa.event_manager.models.Host;
import com.spring_data_jpa.event_manager.services.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public ResponseEntity<List<Host>> getAllHosts(){
        return ResponseEntity.status(HttpStatus.OK).body(hostService.getAllHosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> getHostById(@PathVariable UUID id){
        Optional<Host> host = hostService.getHostById(id);
        return host.map(ResponseEntity::ok)
                .orElseGet(()->
        ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody HostRecordDto hostRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(hostService.createHost(hostRecordDto));
    }




}