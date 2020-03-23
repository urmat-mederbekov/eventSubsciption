package kg.attractor.eventsubscription.controller;

import kg.attractor.eventsubscription.dto.EventDTO;
import kg.attractor.eventsubscription.dto.SubscriptionDTO;
import kg.attractor.eventsubscription.service.SubscriptionService;
import kz.attractorschool.microgram.annotations.ApiPageable;
import kg.attractor.eventsubscription.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final SubscriptionService subscriptionService;

    public EventController(EventService eventService, SubscriptionService subscriptionService) {
        this.eventService = eventService;
        this.subscriptionService = subscriptionService;
    }

    @ApiPageable
    @GetMapping
    public Slice<EventDTO> findEvents(@ApiIgnore Pageable pageable){
        return eventService.findEvents(pageable);
    }

    @ApiPageable
    @GetMapping("/{email}/subscriptions")
    public Page<SubscriptionDTO> findSubscriptionByEmail(@ApiIgnore Pageable pageable,
                                                         @PathVariable String email){
        return subscriptionService.findSubscriptionsByEmail(pageable, email);
    }
}
