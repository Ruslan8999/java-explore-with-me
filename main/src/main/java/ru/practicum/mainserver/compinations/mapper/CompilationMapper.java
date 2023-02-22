package ru.practicum.mainserver.compinations.mapper;

import ru.practicum.mainserver.compinations.dto.CompilationInDto;
import ru.practicum.mainserver.compinations.dto.CompilationOutDto;
import ru.practicum.mainserver.compinations.model.Compilation;
import ru.practicum.mainserver.events.mapper.EventMapper;

import java.util.stream.Collectors;

public class CompilationMapper {
    public static CompilationOutDto toCompilationOutDto(Compilation compilation) {
        return CompilationOutDto.builder()
                .id(compilation.getId())
                .title(compilation.getTitle())
                .pinned(compilation.getPinned())
                .events(compilation.getEvents().stream()
                        .map(EventMapper::toEventShortDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Compilation toCompilation(Compilation compilation, CompilationInDto compilationDto) {
        if (compilationDto.getId() != null) compilation.setId(compilationDto.getId());
        if (compilationDto.getPinned() != null) compilation.setPinned(compilationDto.getPinned());
        if (compilationDto.getTitle() != null) compilation.setTitle(compilationDto.getTitle());
        return compilation;
    }
}
