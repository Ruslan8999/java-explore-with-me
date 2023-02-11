package ru.practicum.mainserver.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.practicum.mainserver.user.model.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findUsersByIdIn(List<Long> ids, Pageable pageable);

    List<User> findUsersByIdIn(List<Long> ids);
}

