package kg.attractor.eventsubscription.service;

import kg.attractor.eventsubscription.dto.EventDTO;
import kg.attractor.eventsubscription.model.Event;
import kg.attractor.eventsubscription.repository.EventRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Slice<EventDTO> findEvents(Pageable pageable){
        Slice<Event> events = eventRepo.findAll(pageable);
        return events.map(EventDTO::from);
    }
}
