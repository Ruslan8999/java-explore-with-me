package ru.practicum.mainserver.requests.service;

import ru.practicum.mainserver.requests.dto.EventRequestDto;
import ru.practicum.mainserver.requests.dto.EventRequestListDto;
import ru.practicum.mainserver.requests.dto.EventRequestStatusUpdateRequest;

import java.util.List;

public interface EventRequestService {
    EventRequestDto createEventRequest(Long userId, Long eventId);

    EventRequestDto updateCategory(Long userId, Long request);

    EventRequestListDto updateEventRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest);

    List<EventRequestDto> getAllEventRequest(Long userId);
}
