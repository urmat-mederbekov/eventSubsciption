package kg.attractor.eventsubscription.service;

import kg.attractor.eventsubscription.dto.SubscriptionDTO;
import kg.attractor.eventsubscription.model.Event;
import kg.attractor.eventsubscription.model.Subscription;
import kz.attractorschool.microgram.exception.ResourceNotFoundException;
import kg.attractor.eventsubscription.repository.EventRepo;
import kg.attractor.eventsubscription.repository.SubscriptionRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubscriptionService {
    private final SubscriptionRepo subscriptionRepo;
    private final EventRepo eventRepo;

    public SubscriptionService(SubscriptionRepo subscriptionRepo, EventRepo eventRepo) {
        this.subscriptionRepo = subscriptionRepo;
        this.eventRepo = eventRepo;
    }

    public Page<SubscriptionDTO> findSubscriptionsByEmail(Pageable pageable, String email){
        Page<Subscription> subscriptions = subscriptionRepo.findAllByEmail(pageable, email);
        return subscriptions.map(SubscriptionDTO::from);
    }

    public SubscriptionDTO addSubscription(String eventId, String email){
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find event with the id: " + eventId));
        if(LocalDateTime.now().isBefore(event.getDateTime())) {
            Subscription subscription = Subscription.builder()
                    .id(UUID.randomUUID().toString())
                    .dateTime(LocalDateTime.now())
                    .email(email)
                    .event(event)
                    .build();
            subscriptionRepo.save(subscription);

            return SubscriptionDTO.from(subscription);
        }

        return null;
    }

    public boolean deleteSubscription(String subscriptionId, String email){
        subscriptionRepo.deleteByIdAndAndEmail(subscriptionId, email);
        return true;
    }
}
