package kg.attractor.eventsubscription.dto;

import kg.attractor.eventsubscription.model.Event;
import kg.attractor.eventsubscription.model.Subscription;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class SubscriptionDTO {
    public static SubscriptionDTO from(Subscription subscription){
        return builder()
                .id(subscription.getId())
                .event(subscription.getEvent())
                .email(subscription.getEmail())
                .dateTime(subscription.getDateTime())
                .build();
    }

    private String id;
    private Event event;
    private String email;
    private LocalDateTime dateTime;
}
