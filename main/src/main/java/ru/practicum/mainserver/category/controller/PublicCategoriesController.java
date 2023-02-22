package ru.practicum.mainserver.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.category.dto.CategoryDto;
import ru.practicum.mainserver.category.service.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
@Slf4j
public class PublicCategoriesController {
    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryDto> findAll(@PositiveOrZero @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                     @Positive @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        log.info("Public findAllCategories: {},{}", from, size);
        return categoryService.getAllCategoriesPublic(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryById(@PathVariable Long catId) {
        log.info("Public getCategoryById: {}", catId);
        return categoryService.getCategoryPublic(catId);
    }
}
