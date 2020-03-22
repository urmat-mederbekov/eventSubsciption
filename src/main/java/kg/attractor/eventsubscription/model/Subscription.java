package kg.attractor.eventsubscription.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder
@Document(collection = "subscriptions")
public class Subscription {

    @Id
    private String id;
    @DBRef
    private Event event;
    private String email;
    private LocalDateTime dateTime;

    public Subscription(Event event, String email) {
        this.id = UUID.randomUUID().toString();
        this.event = event;
        this.email = email;
        this.dateTime = LocalDateTime.now();
    }
}
