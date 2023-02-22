package ru.practicum.mainserver.comment.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class CommentOutDto {
    Long id;
    String text;
    LocalDateTime created;
    String authorName;
}
