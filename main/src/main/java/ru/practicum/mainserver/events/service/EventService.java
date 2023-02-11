package ru.practicum.mainserver.events.service;

import ru.practicum.mainserver.events.dto.EventInDto;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.EventShortDto;
import ru.practicum.mainserver.events.dto.UpdateEventUserRequest;
import ru.practicum.mainserver.requests.dto.EventRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventOutDto createEventPrivate(Long userId, EventInDto eventDto);

    EventOutDto updateEventPrivate(Long userId, Long eventId, UpdateEventUserRequest eventDto);

    EventOutDto getEventPublic(Long id, HttpServletRequest request);

    List<EventShortDto> getAllEventsPublic(String text, List<String> categories, Boolean paid, Boolean onlyAvailable, LocalDateTime start, LocalDateTime end, String sort, Integer from, Integer size, HttpServletRequest request);

    List<EventOutDto> getAllEventsAdmin(List<Long> users, List<String> states, List<Long> categories, LocalDateTime start, LocalDateTime end, Integer from, Integer size);

    List<EventShortDto> getAllEventsPrivate(Long userId, Integer from, Integer size);

    EventOutDto getEventPrivate(Long userId, Long eventId);

    List<EventRequestDto> getEventRequestsPrivate(Long userId, Long eventId);

    EventOutDto updateEventAdmin(Long eventId, UpdateEventUserRequest eventDtoRequest);
}
