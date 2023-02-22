package ru.practicum.mainserver.category.service;

import ru.practicum.mainserver.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategoryAdmin(CategoryDto userDto);

    CategoryDto updateCategoryAdmin(Long id, CategoryDto userDto);

    void removeCategoryAdmin(Long id);

    List<CategoryDto> getAllCategoriesPublic(Integer from, Integer size);

    CategoryDto getCategoryPublic(Long id);

}
