package ru.practicum.mainserver.events.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.practicum.mainserver.events.model.Event;
import ru.practicum.mainserver.user.model.User;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends PagingAndSortingRepository<Event, Long>, EventCustomRepository {
    List<Event> findAllByIdIn(List<Long> ids);

    Optional<Event> findByInitiatorAndId(User u, Long id);

    Page<Event> findAllByInitiator(User u, Pageable pageable);

    Event findEventByInitiatorAndId(User u, Long eventId);

}
