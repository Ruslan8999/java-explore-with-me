package ru.practicum.mainserver.requests.dto;

import lombok.*;
import lombok.experimental.NonFinal;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Value
public class EventRequestListDto {
    @NonFinal
    List<EventRequestDto> confirmedRequests;
    @NonFinal
    List<EventRequestDto> rejectedRequests;
}
