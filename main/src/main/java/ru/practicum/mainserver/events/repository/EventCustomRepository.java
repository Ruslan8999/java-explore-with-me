package ru.practicum.mainserver.events.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.practicum.mainserver.category.model.Category;
import ru.practicum.mainserver.events.model.Event;
import ru.practicum.mainserver.events.model.State;
import ru.practicum.mainserver.user.model.User;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


public interface EventCustomRepository {

    Page<Event> findAllEventsForAdminCustom(List<User> initiator,
                                            List<State> states,
                                            LocalDateTime eventDate,
                                            LocalDateTime eventDate2,
                                            Collection<Category> category,
                                            Pageable pageable);

    Page<Event> findAllEventsForPublicCustom(String annotation,
                                             String description,
                                             LocalDateTime start,
                                             LocalDateTime end,
                                             Boolean paid,
                                             Collection<Category> category,
                                             Pageable pageable);
}

