package ru.practicum.mainserver.requests.mapper;

import ru.practicum.mainserver.requests.dto.EventRequestDto;
import ru.practicum.mainserver.requests.model.EventRequest;

public class EventRequestMapper {
    public static EventRequestDto toEventRequestDto(EventRequest eventRequest) {
        return EventRequestDto.builder()
                .id(eventRequest.getId())
                .event(eventRequest.getEvent().getId())
                .requester(eventRequest.getRequester().getId())
                .status(eventRequest.getState())
                .created(eventRequest.getCreated())
                .build();
    }
}
