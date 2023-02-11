package ru.practicum.mainserver.category.mapper;

import ru.practicum.mainserver.category.dto.CategoryDto;
import ru.practicum.mainserver.category.model.Category;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toCategory(Category category, CategoryDto categoryDto) {
        if (categoryDto.getId() != null) category.setId(categoryDto.getId());
        if (categoryDto.getName() != null) category.setName(categoryDto.getName());
        return category;
    }
}
