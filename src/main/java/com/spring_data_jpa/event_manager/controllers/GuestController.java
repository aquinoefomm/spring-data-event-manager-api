package com.spring_data_jpa.event_manager.controllers;

import com.spring_data_jpa.event_manager.dtos.GuestRecordDto;
import com.spring_data_jpa.event_manager.models.Guest;
import com.spring_data_jpa.event_manager.services.GuestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List> getAllGuests(){
        return ResponseEntity.status(HttpStatus.OK).body(guestService.getAllGuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable UUID id){
        Optional<Guest> guest = guestService.getGuestById(id);
        return guest.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@Valid @RequestBody GuestRecordDto guestRecordDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.createGuest(guestRecordDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        guestService.deleteById(id);
    }


}