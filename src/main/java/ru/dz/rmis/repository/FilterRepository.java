package ru.dz.rmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.rmis.model.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long> {

}
