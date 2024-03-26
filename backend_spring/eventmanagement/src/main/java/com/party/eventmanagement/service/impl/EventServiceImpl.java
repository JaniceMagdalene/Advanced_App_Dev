package com.party.eventmanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.party.eventmanagement.dto.request.EventRequest;
import com.party.eventmanagement.dto.request.EventUpdateRequest;
import com.party.eventmanagement.model.Event;
import com.party.eventmanagement.repository.EventRepo;
import com.party.eventmanagement.service.EventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private EventRepo eventRepo;

    @Override
    public void createEvent(EventRequest eventRequest)
    {
    Event event =new Event();
    event.setEventName(eventRequest.getEventName());
    event.setDescrption(eventRequest.getDescrption());
    event.setCost(eventRequest.getCost());
    eventRepo.save(event);     
    }

    @Override
    public void updateEvent(long eid,EventUpdateRequest eventUpdateRequest){
        Event existingevent=eventRepo.findById(eid)
        .orElseThrow(() -> new RuntimeException("event Not Found"));
        existingevent.setEventName(eventUpdateRequest.getEventName());
        existingevent.setDescrption(eventUpdateRequest.getDescrption());
        existingevent.setCost(eventUpdateRequest.getCost());
    }

    @Override
    public void deleteEvent(long eid) {
       eventRepo.deleteById(eid);
    }
    @Override
    public List<Event> getAllEvent() {
        return eventRepo.findAll();
    }
}
