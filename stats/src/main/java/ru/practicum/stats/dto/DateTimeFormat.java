package ru.practicum.stats.dto;

import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
}
