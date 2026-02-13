package ru.fsog.tmhHacaton2026.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;

import java.io.IOException;
import java.util.List;

public interface DefectService {

    DefectDetailsDTO createDefect(MultipartFile file, DefectDetailsDTO data) throws IOException;

    List<DefectDetailsDTO> getAllDefects(Pageable pageable);



}
