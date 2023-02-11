package ru.practicum.mainserver.events.mapper;

import ru.practicum.mainserver.events.dto.EventInDto;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.EventShortDto;
import ru.practicum.mainserver.events.dto.UpdateEventUserRequest;
import ru.practicum.mainserver.events.model.Event;

public class EventMapper {
    public static EventOutDto toEventDto(Event event) {
        return EventOutDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .title(event.getTitle())
                .eventDate(event.getEventDate())
                .paid(event.getPaid())
                .views(event.getViews())
                .confirmedRequests(event.getConfirmedRequests())
                .initiator(event.getInitiator())
                .category(event.getCategory())
                .description(event.getDescription())
                .location(event.getLocation())
                .requestModeration(event.getRequestModeration())
                .participantLimit(event.getParticipantLimit())
                .state(event.getState())
                .createdOn(event.getCreatedOn())
                .publishedOn(event.getPublishedOn())
                .build();
    }

    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .title(event.getTitle())
                .eventDate(event.getEventDate())
                .paid(event.getPaid())
                .views(event.getViews())
                .confirmedRequests(event.getConfirmedRequests())
                .initiator(event.getInitiator())
                .category(event.getCategory())
                .build();
    }

    public static Event toEvent(Event event, EventInDto eventDto) {
        if (eventDto.getId() != null) event.setId(eventDto.getId());
        if (eventDto.getAnnotation() != null) event.setAnnotation(eventDto.getAnnotation());
        if (eventDto.getTitle() != null) event.setTitle(eventDto.getTitle());
        if (eventDto.getEventDate() != null) event.setEventDate(eventDto.getEventDate());
        if (eventDto.getPaid() != null) event.setPaid(eventDto.getPaid());
        if (eventDto.getDescription() != null) event.setDescription(eventDto.getDescription());
        if (eventDto.getLocation() != null) event.setLocation(eventDto.getLocation());
        if (eventDto.getRequestModeration() != null) event.setRequestModeration(eventDto.getRequestModeration());
        if (eventDto.getParticipantLimit() != null) event.setParticipantLimit(eventDto.getParticipantLimit());
        return event;

    }

    public static Event toEvent(Event event, UpdateEventUserRequest eventDto) {
        if (eventDto.getId() != null) event.setId(eventDto.getId());
        if (eventDto.getAnnotation() != null) event.setAnnotation(eventDto.getAnnotation());
        if (eventDto.getTitle() != null) event.setTitle(eventDto.getTitle());
        if (eventDto.getEventDate() != null) event.setEventDate(eventDto.getEventDate());
        if (eventDto.getPaid() != null) event.setPaid(eventDto.getPaid());
        if (eventDto.getDescription() != null) event.setDescription(eventDto.getDescription());
        if (eventDto.getLocation() != null) event.setLocation(eventDto.getLocation());
        if (eventDto.getRequestModeration() != null) event.setRequestModeration(eventDto.getRequestModeration());
        if (eventDto.getParticipantLimit() != null) event.setParticipantLimit(eventDto.getParticipantLimit());
        return event;
    }
}

