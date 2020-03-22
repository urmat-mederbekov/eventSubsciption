package kg.attractor.eventsubscription.controller;

import kg.attractor.eventsubscription.dto.EventDTO;
import kz.attractorschool.microgram.annotations.ApiPageable;
import kg.attractor.eventsubscription.service.EventService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @ApiPageable
    @GetMapping
    public Slice<EventDTO> findEvents(@ApiIgnore Pageable pageable){
        return eventService.findEvents(pageable);
    }
}
