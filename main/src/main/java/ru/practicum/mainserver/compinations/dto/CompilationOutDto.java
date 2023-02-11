package ru.practicum.mainserver.compinations.dto;

import lombok.*;
import lombok.experimental.NonFinal;
import ru.practicum.mainserver.events.dto.EventShortDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Value
public class CompilationOutDto {
    @NonFinal
    Long id;
    @NonFinal
    List<EventShortDto> events;
    @NonFinal
    Boolean pinned;
    @NonFinal
    @NotNull
    String title;
}
