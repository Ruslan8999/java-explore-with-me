package ru.practicum.mainserver.compinations.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.compinations.dto.CompilationInDto;
import ru.practicum.mainserver.compinations.dto.CompilationOutDto;
import ru.practicum.mainserver.compinations.mapper.CompilationMapper;
import ru.practicum.mainserver.compinations.model.Compilation;
import ru.practicum.mainserver.compinations.repository.CompilationRepository;
import ru.practicum.mainserver.events.repository.EventRepository;
import ru.practicum.mainserver.exceptions.ResourceNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    @Override
    public CompilationOutDto addCompilationAdmin(CompilationInDto compilationInDto) {
        Compilation compilation = CompilationMapper.toCompilation(new Compilation(), compilationInDto);
        if (compilationInDto.getEvents() != null) {
            compilation.setEvents(eventRepository.findAllByIdIn(compilationInDto.getEvents()));
        }
        Compilation createdCategory = compilationRepository.save(compilation);
        log.info("Category created" + createdCategory);
        return CompilationMapper.toCompilationOutDto(createdCategory);
    }

    @Override
    public CompilationOutDto updateCompilationAdmin(Long id, CompilationInDto compilationInDto) {
        Compilation updatedCategory;
        var compilationOptional = compilationRepository.findById(id);
        if (compilationOptional.isPresent()) {
            var compilation = compilationOptional.get();
            CompilationMapper.toCompilation(compilation, compilationInDto);
            compilation.setId(id);
            if (compilationInDto.getEvents() != null) {
                compilation.setEvents(eventRepository.findAllByIdIn(compilationInDto.getEvents()));
            }
            updatedCategory = compilationRepository.save(compilation);
            log.info("Category updated" + updatedCategory);
            return CompilationMapper.toCompilationOutDto(updatedCategory);
        } else {
            throw new ResourceNotFoundException(String.format("Compilation with id=%d not found",id));
        }
    }

    @Override
    public void removeCompilation(Long compId) {
        compilationRepository.deleteAllById(Collections.singleton(compId));
    }

    @Override
    public List<CompilationOutDto> findAllCompilations(boolean pinned, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return compilationRepository.findAllByPinnedIs(pinned, pageable).stream()
                .map(CompilationMapper::toCompilationOutDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompilationOutDto findCompilationById(Long compId) {
        return CompilationMapper.toCompilationOutDto(compilationRepository.findCompilationById(compId));
    }
}
