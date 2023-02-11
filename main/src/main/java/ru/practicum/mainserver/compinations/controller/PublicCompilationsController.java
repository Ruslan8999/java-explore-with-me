package ru.practicum.mainserver.compinations.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.compinations.dto.CompilationOutDto;
import ru.practicum.mainserver.compinations.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationsController {

    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationOutDto> findAllCompilations(@RequestParam(name = "pinned", required = false, defaultValue = "false") Boolean pinned,
                                                       @PositiveOrZero
                                                       @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                                       @Positive
                                                       @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Public findAllCompilations: {},{},{}", pinned, from, size);
        return compilationService.findAllCompilations(pinned, from, size);
    }

    @GetMapping("{compId}")
    public CompilationOutDto findCompilationById(@Positive @PathVariable Long compId) {
        log.info("Public findCompilationById: {}", compId);
        return compilationService.findCompilationById(compId);
    }
}
