package ru.practicum.mainserver.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mainserver.category.dto.CategoryDto;
import ru.practicum.mainserver.category.mapper.CategoryMapper;
import org.springframework.data.domain.Pageable;
import ru.practicum.mainserver.category.model.Category;
import ru.practicum.mainserver.category.repository.CategoriesRepository;
import ru.practicum.mainserver.exceptions.ResourceNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public CategoryDto addCategoryAdmin(CategoryDto categoryDto) {
        Category category = CategoryMapper.toCategory(new Category(), categoryDto);
        Category createdCategory = categoriesRepository.save(category);
        log.info("Category has been created" + createdCategory);
        return CategoryMapper.toCategoryDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategoryAdmin(Long id, CategoryDto categoryDto) {
        Category updatedCategory;
        var categoryOptional = categoriesRepository.findById(id);
        if (categoryOptional.isPresent()) {
            var category = categoryOptional.get();
            CategoryMapper.toCategory(category, categoryDto);
            category.setId(id);
            updatedCategory = categoriesRepository.save(category);
            log.info("Category updated" + updatedCategory);
            return CategoryMapper.toCategoryDto(updatedCategory);
        } else {
            throw new ResourceNotFoundException(String.format("Category with id=%d not found",id));
        }
    }

    @Override
    public void removeCategoryAdmin(Long id) {
        checkCategoryExists(id);
        categoriesRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public List<CategoryDto> getAllCategoriesPublic(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        return categoriesRepository.findAll(pageable).stream()
                .map(CategoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryPublic(Long id) {
        Optional<Category> category = categoriesRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Category with id=%d not found", id));
        }
        return CategoryMapper.toCategoryDto(category.get());
    }

    private void checkCategoryExists(Long catId) throws ResourceNotFoundException {
        if (!categoriesRepository.existsById(catId)) {
            throw new ResourceNotFoundException("Category ID was not found.");
        }
    }
}
