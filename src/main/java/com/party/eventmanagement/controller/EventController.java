package com.party.eventmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.eventmanagement.dto.request.EventRequest;
import com.party.eventmanagement.dto.request.EventUpdateRequest;
import com.party.eventmanagement.dto.response.MessageResponse;
import com.party.eventmanagement.model.Event;
import com.party.eventmanagement.repository.EventRepo;
import com.party.eventmanagement.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Tag(name = "Admin Controls", description = "Endpoints for admin")
public class EventController {
    private final EventService eventService;
    private final EventRepo eventRepository;

    @PostMapping("/AddEvent")
    @PreAuthorize("hasRole('Admin')")
    @Operation(summary = "Add Event", description = "Allows admin to create Event.")

    public ResponseEntity<?> createEvent(@Valid @RequestBody EventRequest eventRequest) {
	    if (eventRepository.existsByEventName(eventRequest.getEventName())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Event already enrolled!"));
	    }

		Event Event = new Event();
        Event.setEventName(eventRequest.getEventName());
        Event.setDescrption(eventRequest.getDescrption());
		Event.setCost(eventRequest.getCost());
        eventRepository.save(Event);
        return ResponseEntity.ok(new MessageResponse("Event added successfully!"));
    }

    @PutMapping("/Event/{EventId}")
	@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateEvent(@PathVariable Long EventId, @RequestBody EventUpdateRequest EventUpdateRequest) {
	    Event existingEvent = eventRepository.findById(EventId)
	            .orElseThrow(() -> new RuntimeException("Error: Event not found."));

	    existingEvent.setEventName(EventUpdateRequest.getEventName());
	    existingEvent.setDescrption(EventUpdateRequest.getDescrption());
	    existingEvent.setCost(EventUpdateRequest.getCost());
        eventRepository.save(existingEvent);
        return ResponseEntity.ok(new MessageResponse("User updated successfully."));
    }


    @DeleteMapping("/Event/{eventId}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
	    eventService.deleteEvent(eventId);
	    return ResponseEntity.ok(new MessageResponse("Event deleted successfully."));
	}

}