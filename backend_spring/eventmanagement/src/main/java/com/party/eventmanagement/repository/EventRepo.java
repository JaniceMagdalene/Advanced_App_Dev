package com.party.eventmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.party.eventmanagement.model.Event;


public interface EventRepo extends JpaRepository<Event, Long> {
    Optional<Event> findByEventName(String eventName);
    Boolean existsByEventName(String eventName);
}
