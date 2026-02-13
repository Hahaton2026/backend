package ru.fsog.tmhHacaton2026.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.entity.Defect;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;
import ru.fsog.tmhHacaton2026.entity.Photo;
import ru.fsog.tmhHacaton2026.exception.DefectTypesNotFoundException;
import ru.fsog.tmhHacaton2026.repository.DefectRepository;
import ru.fsog.tmhHacaton2026.repository.DefectTypesRepository;
import ru.fsog.tmhHacaton2026.repository.PhotoRepository;
import ru.fsog.tmhHacaton2026.service.DefectService;
import ru.fsog.tmhHacaton2026.util.DefectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepository defectRepository;
    private final DefectTypesRepository defectTypesRepository;
    private final PhotoRepository photoRepository;

    @Override
    public DefectDetailsDTO createDefect(MultipartFile file, DefectDetailsDTO data) throws IOException {

        Optional<DefectTypes> optionalDefectTypes = defectTypesRepository.findByName(data.getTypeName());
        if (optionalDefectTypes.isEmpty()) {
            throw new DefectTypesNotFoundException("Defect type not found");
        }

        Photo photo = new Photo();
        photo.setPhoto(file.getBytes());
        photo.setTimeCreated(LocalDateTime.now());
        photo = photoRepository.save(photo);

        Defect defect = new Defect();
        defect.setId(data.getId());
        defect.setDefectTypes(optionalDefectTypes.get());
        defect.setComment(data.getComment());
        defect.setLine(data.getLine());
        defect.setPhoto(photo);

        return DefectMapper.convertToDto(defectRepository.save(defect));
    }

    @Override
    public List<DefectDetailsDTO> getAllDefects(Pageable pageable) {
        return defectRepository.findAll().stream()
                .map(DefectMapper::convertToDto)
                .collect(Collectors.toList());
    }


}
