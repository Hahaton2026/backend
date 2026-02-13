package ru.fsog.tmhHacaton2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fsog.tmhHacaton2026.entity.Defect;

import java.util.List;

public interface DefectRepository extends JpaRepository<Defect, Long> {
    @Override
    Page<Defect> findAll(Pageable pageable);
}
