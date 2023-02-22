package ru.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stats.dto.DateTimeFormat;
import ru.practicum.stats.dto.StatInDto;
import ru.practicum.stats.dto.StatOutDto;
import ru.practicum.stats.mapper.StatMapper;
import ru.practicum.stats.model.Stat;
import ru.practicum.stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;

    @Override
    public void saveHit(StatInDto statInDto) {
        Stat stat = StatMapper.toStat(statInDto);
        statsRepository.save(stat);

    }

    @Override
    public List<StatOutDto> getHit(String start, String end, List<String> uris, Boolean unique) {
        List<StatOutDto> stats;
        if (uris.size() != 0) {
            if (unique) {
                stats = statsRepository.countByTimestampAndListUniqueIp(
                        LocalDateTime.parse(start, DateTimeFormat.DATE_TIME_FORMATTER),
                        LocalDateTime.parse(end, DateTimeFormat.DATE_TIME_FORMATTER),
                        uris);
            } else {
                stats = statsRepository.countByTimestampAndList(
                        LocalDateTime.parse(start, DateTimeFormat.DATE_TIME_FORMATTER),
                        LocalDateTime.parse(end, DateTimeFormat.DATE_TIME_FORMATTER),
                        uris);
            }
        } else {
            if (unique) {
                stats = statsRepository.countByTimestampUniqueIp(
                        LocalDateTime.parse(start, DateTimeFormat.DATE_TIME_FORMATTER),
                        LocalDateTime.parse(end, DateTimeFormat.DATE_TIME_FORMATTER));
            } else {
                stats = statsRepository.countByTimestamp(
                        LocalDateTime.parse(start, DateTimeFormat.DATE_TIME_FORMATTER),
                        LocalDateTime.parse(end, DateTimeFormat.DATE_TIME_FORMATTER));
            }
        }
        return stats;
    }
}
