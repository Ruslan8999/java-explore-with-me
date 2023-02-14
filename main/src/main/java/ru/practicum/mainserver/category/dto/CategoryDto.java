package ru.practicum.mainserver.category.dto;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@Value
public class CategoryDto {
    @NonFinal
    Long id;
    @NonFinal
    @NotNull
    String name;
}
