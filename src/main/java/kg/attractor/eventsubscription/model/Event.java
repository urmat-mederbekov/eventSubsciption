package kg.attractor.eventsubscription.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime dateTime;

    public Event(String name, String description, LocalDateTime dateTime) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Event(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }
}
