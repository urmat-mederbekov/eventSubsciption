package kg.attractor.eventsubscription.controller;

import kg.attractor.eventsubscription.dto.SubscriptionDTO;
import kg.attractor.eventsubscription.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{eventId}&{email}")
    public SubscriptionDTO subscribe(@PathVariable String eventId, @PathVariable String email){
        return subscriptionService.addSubscription(eventId, email);
    }

    @DeleteMapping("/{subscriptionId}&{email}")
    public ResponseEntity<Void> unsubscribe(@PathVariable String subscriptionId,
                                            @PathVariable String email){
        if(subscriptionService.deleteSubscription(subscriptionId, email))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
