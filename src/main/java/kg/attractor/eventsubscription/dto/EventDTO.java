package kg.attractor.eventsubscription.dto;

import kg.attractor.eventsubscription.model.Event;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventDTO {
    public static EventDTO from(Event event){
        return builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .dateTime(event.getDateTime())
                .build();
    }

    private String id;
    private String name;
    private String description;
    private LocalDateTime dateTime;
}
