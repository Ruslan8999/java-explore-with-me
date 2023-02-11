package ru.practicum.mainserver.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainserver.category.model.Category;
import ru.practicum.mainserver.user.model.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Value
public class EventShortDto {
    @NonFinal
    Long id;
    @NonFinal
    String annotation;
    @NonFinal
    Category category;
    @NonFinal
    Long confirmedRequests;
    @NonFinal
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @NonFinal
    User initiator;
    @NonFinal
    Boolean paid;
    @NonFinal
    String title;
    @NonFinal
    Long views;
}