package ru.practicum.mainserver.compinations.service;

import ru.practicum.mainserver.compinations.dto.CompilationInDto;
import ru.practicum.mainserver.compinations.dto.CompilationOutDto;

import java.util.List;

public interface CompilationService {
    CompilationOutDto addCompilationAdmin(CompilationInDto compilationInDto);

    CompilationOutDto updateCompilationAdmin(Long id, CompilationInDto compilationInDto);

    void removeCompilation(Long compId);

    List<CompilationOutDto> findAllCompilations(boolean pinned, Integer from, Integer size);

    CompilationOutDto findCompilationById(Long compId);
}
