package ru.practicum.mainserver.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.NonFinal;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.mainserver.category.model.Category;
import ru.practicum.mainserver.events.model.Location;
import ru.practicum.mainserver.events.model.State;
import ru.practicum.mainserver.user.model.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Value
public class EventOutDto {
    @NonFinal
    Long id;
    @NonFinal
    String annotation;
    @NonFinal
    String description;
    @NonFinal
    String title;
    @NonFinal
    Category category;
    @NonFinal
    User initiator;
    @NonFinal
    Long confirmedRequests;
    @NonFinal
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    @NonFinal
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime publishedOn;
    @NonFinal
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdOn;
    @NonFinal
    Boolean paid;
    @NonFinal
    Long views;
    @NonFinal
    Boolean requestModeration;
    @NonFinal
    Long participantLimit;
    @NonFinal
    Location location;
    @NonFinal
    State state;
}
