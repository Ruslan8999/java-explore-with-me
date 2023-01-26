package ru.practicum.stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats.dto.StatOutDto;
import ru.practicum.stats.model.Stat;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stat, Long> {
    @Query(value = "SELECT new ru.practicum.stats.dto.StatOutDto(s.app, s.uri, COUNT (s.id)) " +
            "FROM Stat s " +
            "WHERE s.uri IN :uris AND s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri, s.ip")
    List<StatOutDto> countByTimestampAndListUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "SELECT new ru.practicum.stats.dto.StatOutDto(s.app, s.uri, COUNT (s.id)) " +
            "FROM Stat s " +
            "WHERE s.uri IN :uris AND s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT (s.id) DESC")
    List<StatOutDto> countByTimestampAndList(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query(value = "SELECT new ru.practicum.stats.dto.StatOutDto(s.app, s.uri, COUNT (s.id)) " +
            "FROM Stat s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri, s.ip ")
    List<StatOutDto> countByTimestampUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query(value = "SELECT new ru.practicum.stats.dto.StatOutDto(s.app, s.uri, COUNT (s.id)) " +
            "FROM Stat s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri ")
    List<StatOutDto> countByTimestamp(LocalDateTime start, LocalDateTime end);
}
