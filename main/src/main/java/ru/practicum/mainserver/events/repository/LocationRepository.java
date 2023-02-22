package ru.practicum.mainserver.events.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.practicum.mainserver.events.model.Location;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
}
