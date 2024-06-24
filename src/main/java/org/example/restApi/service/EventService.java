package org.example.restApi.service;

import lombok.RequiredArgsConstructor;
import org.example.restApi.model.Event;
import org.example.restApi.repository.EventRepository;
import org.example.restApi.repository.impl.HibernateEventRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new HibernateEventRepositoryImpl();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        return eventRepository.update(id, event);
    }

    public void deleteEvent(Long id) {
        eventRepository.delete(id);
    }
}
