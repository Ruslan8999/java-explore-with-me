package ru.practicum.stats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.Constants;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatInDto {
    @NotNull
    String app;
    @NotNull
    String uri;
    @NotNull
    String ip;
    @NotNull
    @JsonFormat(pattern = Constants.DATE_TIME_STRING)
    LocalDateTime timestamp;
}
