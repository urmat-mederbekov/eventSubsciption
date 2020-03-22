package kg.attractor.eventsubscription.util;

import kg.attractor.eventsubscription.model.Event;
import kg.attractor.eventsubscription.model.Subscription;
import kz.attractorschool.microgram.utils.Generator;
import kg.attractor.eventsubscription.repository.EventRepo;
import kg.attractor.eventsubscription.repository.SubscriptionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class DataPreloader {

    List<Event> events = makeEvents();
    List<Subscription> subscriptions = makeSubscriptions();

    @Bean
    CommandLineRunner preloadDatabase(SubscriptionRepo subscriptionRepo, EventRepo eventRepo){
        return args -> {
            subscriptionRepo.deleteAll();
            eventRepo.deleteAll();

            Stream.of(getSubscriptions())
                    .peek(subscriptions -> subscriptions.forEach(System.out::println))
                    .forEach(subscriptionRepo::saveAll);
            Stream.of(getEvents())
                    .peek(events -> events.forEach(System.out::println))
                    .forEach(eventRepo::saveAll);
        };
    }

    private List<Event> makeEvents(){
        List<Event> events = new ArrayList<>();
        events.add(new Event(Generator.makeName(), Generator.makeDescription(), LocalDateTime.now().plusDays(4)));
        events.add(new Event(Generator.makeName(), Generator.makeDescription(), LocalDateTime.now().minusYears(4)));
        events.add(new Event(Generator.makeName(), Generator.makeDescription(), LocalDateTime.now().plusMonths(1)));
        events.add(new Event(Generator.makeName(), Generator.makeDescription()));
        events.add(new Event(Generator.makeName(), Generator.makeDescription()));

        return events;
    }

    private List<Subscription> makeSubscriptions(){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(getEvents().get(2), Generator.makeEmail()));
        subscriptions.add(new Subscription(getEvents().get(3), Generator.makeEmail()));
        subscriptions.add(new Subscription(getEvents().get(0), Generator.makeEmail()));
        subscriptions.add(new Subscription(getEvents().get(0), Generator.makeEmail()));

        return subscriptions;
    }

    private List<Event> getEvents(){
        return events;
    }

    private List<Subscription> getSubscriptions(){return subscriptions;}

}
