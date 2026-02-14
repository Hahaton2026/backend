package ru.fsog.tmhHacaton2026.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.entity.Defect;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;
import ru.fsog.tmhHacaton2026.entity.Photo;
import ru.fsog.tmhHacaton2026.exception.DefectTypesNotFoundException;
import ru.fsog.tmhHacaton2026.exception.PhotoNotFoundException;
import ru.fsog.tmhHacaton2026.repository.DefectRepository;
import ru.fsog.tmhHacaton2026.repository.DefectTypesRepository;
import ru.fsog.tmhHacaton2026.repository.PhotoRepository;
import ru.fsog.tmhHacaton2026.service.DefectService;
import ru.fsog.tmhHacaton2026.util.DefectMapper;

import java.beans.Transient;
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

//    @Override
//    @Transactional
//    public DefectDetailsDTO createDefect(MultipartFile file, DefectDetailsDTO data) throws IOException {
//
//        Optional<DefectTypes> optionalDefectTypes = defectTypesRepository.findByName(data.getTypeName());
//        if (optionalDefectTypes.isEmpty()) {
//            throw new DefectTypesNotFoundException("Defect type not found: " + data.getTypeName());
//        }
//
//        Photo photo = new Photo();
//        photo.setPhoto(file.getBytes());
//        photo.setTimeCreated(LocalDateTime.now());
//        photo = photoRepository.save(photo);
//
//        Defect defect = new Defect();
//        defect.setDefectTypes(optionalDefectTypes.get());
//        defect.setComment(data.getComment());
//        defect.setLine(data.getLine());
//        defect.setPhoto(photo);
//
//        return DefectMapper.convertToDto(defectRepository.save(defect));
//    }

    @Override
    public List<DefectDetailsDTO> getAllDefects(Pageable pageable) {
        return defectRepository.findAll().stream()
                .map(DefectMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DefectDetailsDTO> getAllDefectsPaginated(Pageable pageable) {
        return defectRepository.findAll(pageable).map(DefectMapper::convertToDto);
    }

    @Override
    @Transactional
    public Long savePhoto(MultipartFile file) throws IOException {

        Photo photo = new Photo();
        photo.setPhoto(file.getBytes());
        photo.setTimeCreated(LocalDateTime.now());
        photo = photoRepository.save(photo);

        return photo.getId();
    }

    @Override
    public DefectDetailsDTO saveDescriptionOfPhoto(DefectDetailsDTO data) {

        Optional<DefectTypes> optionalDefectTypes = defectTypesRepository.findByName(data.getTypeName());
        if (optionalDefectTypes.isEmpty()) {
            throw new DefectTypesNotFoundException("Defect type " + data.getTypeName() + " not found");
        }
        Optional<Photo> optionalPhoto = photoRepository.findById(data.getPhotoId());
        if (optionalPhoto.isEmpty()) {
            throw new PhotoNotFoundException("Photo with id="+data.getPhotoId()+" not found");
        }

        Defect defect = new Defect();
        defect.setDefectTypes(optionalDefectTypes.get());
        defect.setComment(data.getComment());
        defect.setLine(data.getLine());
        defect.setPhoto(optionalPhoto.get());

        defect = defectRepository.save(defect);

        return null;
    }


}
