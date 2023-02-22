package ru.practicum.mainserver.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.common.dtoStats.StatInDto;
import ru.practicum.mainserver.category.model.Category;
import ru.practicum.mainserver.category.repository.CategoriesRepository;
import ru.practicum.mainserver.client.AdminStatsClient;
import ru.practicum.mainserver.events.dto.EventInDto;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.EventShortDto;
import ru.practicum.mainserver.events.dto.UpdateEventUserRequest;
import ru.practicum.mainserver.events.mapper.EventMapper;
import ru.practicum.mainserver.events.model.Event;
import ru.practicum.mainserver.events.model.Location;
import ru.practicum.mainserver.events.model.State;
import ru.practicum.mainserver.events.model.StateAction;
import ru.practicum.mainserver.events.repository.EventRepository;
import ru.practicum.mainserver.events.repository.LocationRepository;
import ru.practicum.mainserver.exceptions.ConflictException;
import ru.practicum.mainserver.exceptions.ResourceNotFoundException;
import ru.practicum.mainserver.requests.dto.EventRequestDto;
import ru.practicum.mainserver.requests.mapper.EventRequestMapper;
import ru.practicum.mainserver.requests.repository.EventRequestRepository;
import ru.practicum.mainserver.user.model.User;
import ru.practicum.mainserver.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CategoriesRepository categoriesRepository;
    private final AdminStatsClient adminStatsClient;
    private final EventRequestRepository eventRequestRepository;

    @Override
    public EventOutDto createEventPrivate(Long userId, EventInDto eventDto) {
        if (eventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ConflictException("Incorrect event date");
        }
        Event event = EventMapper.toEvent(new Event(), eventDto);
        event.setState(State.PENDING);
        event.setCreatedOn(LocalDateTime.now().withNano(0));
        var initiator = userRepository.findById(userId);
        if (initiator.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
        } else {
            event.setInitiator(initiator.get());
        }
        if (event.getLocation() != null) {
            Location createdLocation = locationRepository.save(eventDto.getLocation());
            event.setLocation(createdLocation);
        }
        var category = categoriesRepository.findById(eventDto.getCategory());
        category.ifPresent(event::setCategory);

        Event createdUser = eventRepository.save(event);
        log.info("User created" + createdUser);
        return EventMapper.toEventDto(createdUser);
    }

    @Override
    public EventOutDto updateEventPrivate(Long userId, Long eventId, UpdateEventUserRequest eventDto) {
        Event updatedEvent;
        if (eventDto.getEventDate() != null && eventDto.getEventDate().isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ConflictException("Incorrect event date");
        }
        var eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            var event = eventOptional.get();
            if (event.getInitiator().getId().equals(userId)) {
                if (event.getState() == State.PUBLISHED) {
                    throw new ConflictException("Can not be changed");
                }
                EventMapper.toEvent(event, eventDto);
                if (eventDto.getStateAction() != null) {
                    event.setState(StateAction.stringToState(eventDto.getStateAction()));
                }
                event.setId(eventId);
                updatedEvent = eventRepository.save(event);
                log.info("Event updated" + updatedEvent);
                return EventMapper.toEventDto(updatedEvent);
            } else {
                throw new ResourceNotFoundException("Initiator is not correct");
            }
        } else {
            throw new ResourceNotFoundException(String.format("Event with id=%d not found",eventId));
        }
    }

    @Override
    public EventOutDto getEventPublic(Long id, HttpServletRequest request) {
        StatInDto statRequestDto = StatInDto.builder().app("ExploreWithMe")
                .ip(request.getRemoteAddr())
                .uri(request.getRequestURI())
                .timestamp(LocalDateTime.now().withNano(0)).build();
        adminStatsClient.saveHit(statRequestDto);
        var user = eventRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found",id));
        }
        return EventMapper.toEventDto(user.get());
    }

    @Override
    public List<EventShortDto> getAllEventsPublic(String text, List<String> categories, Boolean paid, Boolean onlyAvailable, LocalDateTime start, LocalDateTime end, String sort, Integer from, Integer size, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(from / size, size);
        StatInDto statRequestDto = StatInDto.builder().app("ExploreWithMe")
                .ip(request.getRemoteAddr())
                .uri(request.getRequestURI())
                .timestamp(LocalDateTime.now().withNano(0)).build();
        adminStatsClient.saveHit(statRequestDto);
        List<Category> categoryList = new ArrayList<>();
        if (categories != null) {
            categoryList = categoriesRepository.findAllByNameIn(categories);
        }

        return eventRepository.findAllEventsForPublicCustom(text, text, start, end, paid, categoryList, pageable)
                .stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventOutDto> getAllEventsAdmin(List<Long> users, List<String> states, List<Long> categories, LocalDateTime start, LocalDateTime end, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Category> categoryList = Collections.emptyList();
        List<State> stateList = Collections.emptyList();
        List<User> userList = Collections.emptyList();
        if (categories != null) {
            categoryList = categoriesRepository.findAllByIdIn(categories);
            if (categoryList.size() == 0) {
                categoryList = Collections.emptyList();
            }
        }
        if (states != null) {
            stateList = states.stream().map(State::stringToState).collect(Collectors.toList());
        }
        if (users != null) {
            userList = userRepository.findUsersByIdIn(users);
        }
        return eventRepository.findAllEventsForAdminCustom(userList, stateList, start, end, categoryList, pageable)
                .stream()
                .map(EventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventShortDto> getAllEventsPrivate(Long userId, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        var initiator = userRepository.findById(userId);
        if (initiator.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
        } else {
            return eventRepository.findAllByInitiator(initiator.get(), pageable)
                    .stream()
                    .map(EventMapper::toEventShortDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public EventOutDto getEventPrivate(Long userId, Long eventId) {
        var initiator = userRepository.findById(userId);
        if (initiator.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
        } else {
            return EventMapper.toEventDto(eventRepository.findEventByInitiatorAndId(initiator.get(), eventId));
        }
    }

    @Override
    public List<EventRequestDto> getEventRequestsPrivate(Long userId, Long eventId) {
        var initiator = userRepository.findById(userId);
        if (initiator.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id=%d not found", userId));
        }
        var event = eventRepository.findByInitiatorAndId(initiator.get(), eventId);
        if (event.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Event with id=%d not found", userId));
        }
        return eventRequestRepository.findAllEventRequestsByEventIs(event.get())
                .stream().map(EventRequestMapper::toEventRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventOutDto updateEventAdmin(Long eventId, UpdateEventUserRequest eventDtoRequest) {
        Event updatedEvent;
        if (eventDtoRequest.getEventDate() != null && eventDtoRequest.getEventDate().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new ConflictException("EventDate has to be after now +1Hour");
        }

        var eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            var event = eventOptional.get();
            if (eventDtoRequest.getStateAction() != null) {
                if (StateAction.stringToState(eventDtoRequest.getStateAction()) == event.getState()) {
                    throw new ConflictException("StateAction is the same as State");
                }
                if (StateAction.stringToStateAction(eventDtoRequest.getStateAction()) == StateAction.PUBLISH_EVENT
                        && event.getState() == State.CANCELED) {
                    throw new ConflictException("Incorrect StateAction");
                }
                if (StateAction.stringToStateAction(eventDtoRequest.getStateAction()) == StateAction.REJECT_EVENT
                        && event.getState() == State.PUBLISHED) {
                    throw new ConflictException("Incorrect StateAction");
                }
            }
            EventMapper.toEvent(event, eventDtoRequest);
            if (StateAction.stringToStateAction(eventDtoRequest.getStateAction()) == StateAction.PUBLISH_EVENT) {
                event.setPublishedOn(LocalDateTime.now().withNano(0));
            }
            event.setState(StateAction.stringToState(eventDtoRequest.getStateAction()));
            event.setId(eventId);
            if (eventDtoRequest.getLocation() != null) {
                Location createdLocation = locationRepository.save(eventDtoRequest.getLocation());
                event.setLocation(createdLocation);
            }
            updatedEvent = eventRepository.save(event);
            log.info("Event updated" + updatedEvent);
            return EventMapper.toEventDto(updatedEvent);
        } else {
            throw new ResourceNotFoundException(String.format("Event with id=%d not found",eventId));
        }
    }
}
