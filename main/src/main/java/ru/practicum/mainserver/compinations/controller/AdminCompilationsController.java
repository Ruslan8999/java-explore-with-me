package ru.practicum.mainserver.compinations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.compinations.dto.CompilationInDto;
import ru.practicum.mainserver.compinations.dto.CompilationOutDto;
import ru.practicum.mainserver.compinations.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/admin/compilations")
@RequiredArgsConstructor
@Slf4j
public class AdminCompilationsController {

    private final CompilationService compilationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CompilationOutDto addCompilation(@Valid @RequestBody CompilationInDto compilationInDto) {
        log.info("Admin addCompilation: {}", compilationInDto);
        return compilationService.addCompilationAdmin(compilationInDto);
    }

    @PatchMapping("/{compId}")
    public CompilationOutDto updateCompilation(@PathVariable Long compId, @RequestBody CompilationInDto compilationInDto) {
        log.info("Admin updateCompilation: {},{}", compId, compilationInDto);
        return compilationService.updateCompilationAdmin(compId, compilationInDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{compId}")
    public void removeCompilation(@PathVariable Long compId) {
        log.info("Admin removeCompilation: {}", compId);
        compilationService.removeCompilation(compId);
    }
}
