package ru.practicum.mainserver.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.mainserver.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameIn(List<String> names);

    List<Category> findAllByIdIn(List<Long> ids);

    Optional<Category> findById(Long id);
}
