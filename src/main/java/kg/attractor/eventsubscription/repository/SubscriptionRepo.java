package kg.attractor.eventsubscription.repository;

import kg.attractor.eventsubscription.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubscriptionRepo extends PagingAndSortingRepository<Subscription, String> {
    void deleteByIdAndAndEmail(String id, String email);
    Page<Subscription> findAllByEmail(Pageable pageable, String email);
}
