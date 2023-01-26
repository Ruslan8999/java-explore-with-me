package ru.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stats.dto.StatInDto;
import ru.practicum.stats.dto.StatOutDto;
import ru.practicum.stats.mapper.StatMapper;
import ru.practicum.stats.model.Stat;
import ru.practicum.stats.repository.StatsRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                        LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        uris);
            } else {
                stats = statsRepository.countByTimestampAndList(
                        LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        uris);
            }
        } else {
            if (unique) {
                stats = statsRepository.countByTimestampUniqueIp(
                        LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } else {
                stats = statsRepository.countByTimestamp(
                        LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }
        return stats;
    }
}
