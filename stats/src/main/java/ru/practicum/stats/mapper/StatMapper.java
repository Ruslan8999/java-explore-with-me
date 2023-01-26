package ru.practicum.stats.mapper;

import ru.practicum.stats.dto.StatInDto;
import ru.practicum.stats.model.Stat;

public class StatMapper {
    public static Stat toStat(StatInDto statInDto) {
        return Stat.builder()
                .app(statInDto.getApp())
                .uri(statInDto.getUri())
                .ip(statInDto.getIp())
                .timestamp(statInDto.getTimestamp())
                .build();
    }
}
