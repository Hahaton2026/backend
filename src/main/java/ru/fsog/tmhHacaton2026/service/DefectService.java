package ru.fsog.tmhHacaton2026.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.dto.DefectTypesDTO;

import java.io.IOException;
import java.util.List;

public interface DefectService {

//    DefectDetailsDTO createDefect(MultipartFile file, DefectDetailsDTO data) throws IOException;

    List<DefectDetailsDTO> getAllDefects();

    Page<DefectDetailsDTO> getAllDefectsPaginated(Pageable pageable);

    Long savePhoto(MultipartFile file) throws IOException;

    void deleteDefect(Long id);

    DefectDetailsDTO saveDescriptionOfPhoto(DefectDetailsDTO data);

    List<DefectDetailsDTO> getAllNewDefects();

    public void markAsFixed(long id);

    void addDefectType(DefectTypesDTO data);

}
