package ru.practicum.mainserver.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.events.dto.EventInDto;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.EventShortDto;
import ru.practicum.mainserver.events.dto.UpdateEventUserRequest;
import ru.practicum.mainserver.events.service.EventService;
import ru.practicum.mainserver.requests.dto.EventRequestDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventsController {
    private final EventService eventService;

    @GetMapping
    public List<EventShortDto> findAllByUserIdPrivate(@PathVariable Long userId,
                                                      @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                                      @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return eventService.getAllEventsPrivate(userId, from, size);
    }

    @GetMapping("/{eventId}")
    public EventOutDto findByUserIdAndEventIdPrivate(@PathVariable Long userId,
                                                     @PathVariable Long eventId) {
        return eventService.getEventPrivate(userId, eventId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EventOutDto createEventPrivate(@Valid @RequestBody EventInDto eventDtoRequest,
                                               @PathVariable Long userId) {
        return eventService.createEventPrivate(userId, eventDtoRequest);
    }

    @PatchMapping("/{eventId}")
    public EventOutDto updateEventPrivate(@PathVariable Long userId,
                                               @PathVariable Long eventId,
                                               @Valid @RequestBody UpdateEventUserRequest eventDtoRequest) {
        return eventService.updateEventPrivate(userId, eventId, eventDtoRequest);
    }

    @GetMapping("/{eventId}/requests")
    public List<EventRequestDto> findAllRequests(@PathVariable Long userId,
                                                 @PathVariable Long eventId) {
        return eventService.getEventRequestsPrivate(userId, eventId);
    }
}
