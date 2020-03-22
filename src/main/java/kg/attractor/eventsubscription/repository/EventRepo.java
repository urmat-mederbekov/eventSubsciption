package kg.attractor.eventsubscription.repository;

import kg.attractor.eventsubscription.model.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepo extends PagingAndSortingRepository<Event, String> {
}
