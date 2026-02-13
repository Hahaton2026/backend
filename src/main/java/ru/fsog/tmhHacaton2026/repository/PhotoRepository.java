package ru.fsog.tmhHacaton2026.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fsog.tmhHacaton2026.entity.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Override
    Page<Photo> findAll(Pageable pageable);
}
