package ru.fsog.tmhHacaton2026.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;

import java.util.List;
import java.util.Optional;

public interface DefectTypesRepository extends JpaRepository<DefectTypes, Long> {

    Optional<DefectTypes> findByName(String name);

    @Override
    List<DefectTypes> findAll();
}
