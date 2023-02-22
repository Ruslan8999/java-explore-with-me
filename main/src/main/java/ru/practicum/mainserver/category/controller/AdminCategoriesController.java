package ru.practicum.mainserver.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainserver.category.dto.CategoryDto;
import ru.practicum.mainserver.category.service.CategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class AdminCategoriesController {
    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryDto addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Admin addCategory: {}", categoryDto);
        return categoryService.addCategoryAdmin(categoryDto);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable Long catId, @RequestBody @Valid CategoryDto categoryDto) {
        log.info("Admin updateCategory: {}", categoryDto);
        return categoryService.updateCategoryAdmin(catId, categoryDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{catId}")
    public void removeCategory(@PathVariable Long catId) {
        log.info("Admin removeCategory: {}", catId);
        categoryService.removeCategoryAdmin(catId);
    }
}
