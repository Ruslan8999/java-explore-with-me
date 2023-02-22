package ru.practicum.mainserver.requests.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.requests.dto.EventRequestDto;
import ru.practicum.mainserver.requests.dto.EventRequestListDto;
import ru.practicum.mainserver.requests.dto.EventRequestStatusUpdateRequest;
import ru.practicum.mainserver.requests.service.EventRequestService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class EventRequestControllerPrivate {
    private final EventRequestService eventRequestService;

    @GetMapping("/{userId}/requests")
    public List<EventRequestDto> findAll(@PathVariable Long userId) {
        return eventRequestService.getAllEventRequest(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{userId}/requests")
    public EventRequestDto createEventRequest(@PathVariable Long userId,
                                              @RequestParam(name = "eventId") Long eventId) {
        return eventRequestService.createEventRequest(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public EventRequestDto updateCategory(@PathVariable Long userId,
                                          @PathVariable Long requestId) {
        return eventRequestService.updateCategory(userId, requestId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestListDto updateRequests(@PathVariable Long userId,
                                              @PathVariable Long eventId,
                                              @Valid @RequestBody EventRequestStatusUpdateRequest eventRequestStatusUpdateRequest) {
        return eventRequestService.updateEventRequestStatus(userId, eventId, eventRequestStatusUpdateRequest);
    }
}
