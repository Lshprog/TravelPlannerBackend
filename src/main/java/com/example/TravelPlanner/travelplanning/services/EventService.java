package com.example.TravelPlanner.travelplanning.services;

import com.example.TravelPlanner.travelplanning.dto.event.EventCreateDTO;
import com.example.TravelPlanner.travelplanning.dto.event.EventDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EventService {

    List<EventDTO> listAllEventsByTravelPlan(Long planId);

    EventDTO getEventById(Long eventId);

    EventDTO saveNewEvent(EventCreateDTO eventCreateDTO, Long travelPlanId, UUID userId);

    void updateEvent(EventDTO eventDTO, Long travelPlanId, UUID userId);

    void deleteEvent(Long eventId, UUID userId);

    List<EventDTO> getEventsByDay(String placeStatus, Long travelPlanId, LocalDate day);

    List<EventDTO> getEvents(String placeStatus, Long travelPlanId);
}
