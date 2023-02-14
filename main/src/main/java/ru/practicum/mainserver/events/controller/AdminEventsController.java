package ru.practicum.mainserver.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.UpdateEventUserRequest;
import ru.practicum.mainserver.events.service.EventService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Slf4j
public class AdminEventsController {
    private final EventService eventService;

    @PatchMapping("/{eventId}")
    public EventOutDto updateEvent(@PathVariable Long eventId, @Valid @RequestBody UpdateEventUserRequest eventDtoRequest) {
        log.info("Admin updateEvent: {}, {}", eventId, eventDtoRequest);
        return eventService.updateEventAdmin(eventId, eventDtoRequest);
    }

    @GetMapping()
    public List<EventOutDto> findAllEvents(@RequestParam(name = "text", required = false) List<Long> userIds,
                                     @RequestParam(name = "states", required = false) List<String> states,
                                     @RequestParam(name = "categories", required = false) List<Long> categories,
                                     @DateTimeFormat(pattern = ru.practicum.common.dtoStats.DateTimeFormat.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                     @DateTimeFormat(pattern = ru.practicum.common.dtoStats.DateTimeFormat.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                     @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Admin findAllEvents: {},{},{},{},{},{},{}", userIds, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getAllEventsAdmin(userIds, states, categories, rangeStart, rangeEnd, from, size);
    }
}
