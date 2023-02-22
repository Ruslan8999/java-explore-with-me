package ru.practicum.mainserver.events.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.events.dto.EventOutDto;
import ru.practicum.mainserver.events.dto.EventShortDto;
import ru.practicum.mainserver.events.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
@Slf4j
public class PublicEventsController {
    private final EventService eventService;

    @GetMapping()
    public List<EventShortDto> findAllEvents(@RequestParam(name = "text", required = false) String text,
                                             @RequestParam(name = "categories", required = false) List<String> categories,
                                             @RequestParam(name = "paid", required = false) Boolean paid,
                                             @RequestParam(name = "onlyAvailable", defaultValue = "false", required = false) Boolean onlyAvailable,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
                                             @RequestParam(name = "sort", required = false) String sort,
                                             @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                             HttpServletRequest request) {
        log.info("Public findAllEvents: {},{},{},{},{},{},{},{},{},{}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request);
        return eventService.getAllEventsPublic(text, categories, paid, onlyAvailable, rangeStart, rangeEnd, sort, from, size, request);
    }

    @GetMapping("/{eventId}")
    public EventOutDto findEventById(@PathVariable Long eventId, HttpServletRequest request) {
        log.info("Public findEventById: {},{}", eventId, request);
        return eventService.getEventPublic(eventId, request);
    }
}
