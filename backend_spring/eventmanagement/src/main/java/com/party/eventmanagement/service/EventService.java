package com.party.eventmanagement.service;

import java.util.List;

import com.party.eventmanagement.dto.request.EventRequest;
import com.party.eventmanagement.dto.request.EventUpdateRequest;
import com.party.eventmanagement.model.Event;

public interface EventService {

    void createEvent(EventRequest eventRequest);
    void updateEvent(long eid, EventUpdateRequest eventUpdaterequest);
    void deleteEvent(long eid);
    List<Event> getAllEvent();
}