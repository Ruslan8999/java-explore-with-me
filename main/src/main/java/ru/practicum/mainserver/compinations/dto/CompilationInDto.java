package ru.practicum.mainserver.compinations.dto;

import lombok.*;
import lombok.experimental.NonFinal;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Value
public class CompilationInDto {
    @NonFinal
    Long id;
    @NonFinal
    List<Long> events;
    @NonFinal
    Boolean pinned;
    @NonFinal
    @NotNull
    String title;
}

