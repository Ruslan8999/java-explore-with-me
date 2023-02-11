package ru.practicum.mainserver.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.events.model.Event;
import ru.practicum.mainserver.requests.model.EventRequest;
import ru.practicum.mainserver.user.model.User;

import java.util.List;

public interface EventRequestRepository extends JpaRepository<EventRequest, Long> {
    List<EventRequest> findEventRequestsByRequester(User requester);

    List<EventRequest> findEventRequestsByIdInAndEventIs(List<Long> requestIds, Event event);

    List<EventRequest> findAllEventRequestsByEventIs(Event event);

    EventRequest findAEventRequestByIdIsAndRequesterIs(Long requestId, User requester);
}
