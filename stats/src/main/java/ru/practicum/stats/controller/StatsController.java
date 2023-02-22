package ru.practicum.stats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.dto.StatInDto;
import ru.practicum.stats.dto.StatOutDto;
import ru.practicum.stats.service.StatsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
public class StatsController {
    private final StatsService statsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hit")
    public void saveHit(@Valid @RequestBody StatInDto statInDto) {
        log.info("saveHit: {}", statInDto);
        statsService.saveHit(statInDto);
    }

    @GetMapping("/stats")
    public List<StatOutDto> getHit(@NotNull @RequestParam(name = "start") String start,
                                   @NotNull @RequestParam(name = "end") String end,
                                   @Valid @RequestParam(name = "uris", defaultValue = "", required = false) List<String> uris,
                                   @RequestParam(name = "unique", defaultValue = "false") Boolean unique) {
        log.info("getHit: {},{},{},{}", start, end, uris, unique);
        return statsService.getHit(start, end, uris, unique);
    }
}
