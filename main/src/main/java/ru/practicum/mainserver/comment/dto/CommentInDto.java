package ru.practicum.mainserver.comment.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Builder
@Value
public class CommentInDto {
    @NotEmpty
    String text;
    Long author;
    Long event;
}
