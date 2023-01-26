package ru.practicum.stats.service;

import ru.practicum.stats.dto.StatInDto;
import ru.practicum.stats.dto.StatOutDto;

import java.util.List;

public interface StatsService {
    void saveHit(StatInDto statInDto);
    List<StatOutDto> getHit(String start, String end, List<String> uris, Boolean unique);
}
