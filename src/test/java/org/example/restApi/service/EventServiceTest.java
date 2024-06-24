package org.example.restApi.service;

import org.example.restApi.model.Event;
import org.example.restApi.model.File;
import org.example.restApi.model.User;
import org.example.restApi.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EventServiceTest {
    private final EventRepository eventRepository = Mockito.mock(EventRepository.class);
    private final EventService eventService = new EventService(eventRepository);
    private Event event;
    private User user;
    private File file;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).build();
        file = File.builder().id(1L).build();
        event = Event.builder().id(1L).user(user).file(file).build();
    }

    @Test
    void getAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(event);

        when(eventService.getAllEvents()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(events, result);
    }

    @Test
    void getEventById() {
        when(eventService.getEventById(1L)).thenReturn(event);
        Event result = eventService.getEventById(1L);
        assertNotNull(result);
        assertEquals(event, result);
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(file.getId(), result.getFile().getId());
    }

    @Test
    void saveEvent() {
        when(eventService.saveEvent(event)).thenReturn(event);
        Event result = eventService.saveEvent(event);
        assertNotNull(result);
        assertEquals(event, result);
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(file.getId(), result.getFile().getId());
    }

    @Test
    void updateEvent() {
        when(eventService.updateEvent(1L, event)).thenReturn(event);
        Event result = eventService.updateEvent(1L, event);
        assertNotNull(result);
        assertEquals(event, result);
        assertEquals(user.getId(), result.getUser().getId());
        assertEquals(file.getId(), result.getFile().getId());
    }

    @Test
    void deleteEvent() {
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).delete(1L);
    }
}
