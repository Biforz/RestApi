package org.example.restApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.restApi.model.Event;
import org.example.restApi.service.EventService;
import org.example.restApi.util.ObjMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/events")
public class EventRestControllerV1 extends HttpServlet {
    private EventService eventService;

    @Override
    public void init() {
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<Event> eventList = eventService.getAllEvents();
            ObjMapper.objectToJson(eventList, resp);
        } else {
            Event event = eventService.getEventById(Long.valueOf(id));
            ObjMapper.objectToJson(event, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Event event = new ObjectMapper().readValue(req.getReader(), Event.class);
        eventService.saveEvent(event);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Event event = new ObjectMapper().readValue(req.getReader(), Event.class);
        Long id = event.getId();
        eventService.updateEvent(id, event);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Event event = eventService.getEventById(id);
        eventService.deleteEvent(event.getId());
    }
}
