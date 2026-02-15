package ru.fsog.tmhHacaton2026.service.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fsog.tmhHacaton2026.dto.DefectTypesDTO;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;
import ru.fsog.tmhHacaton2026.repository.DefectTypesRepository;
import ru.fsog.tmhHacaton2026.service.DefectTypeService;
import ru.fsog.tmhHacaton2026.util.DefectMapper;
import ru.fsog.tmhHacaton2026.util.DefectTypesMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectTypeServiceImpl implements DefectTypeService {

    private final DefectTypesRepository defectTypesRepository;


    @Override
    public List<DefectTypesDTO> getAllDefectTypes() {
        return defectTypesRepository.findAll()
                .stream()
                .map(DefectTypesMapper::convertToDto)
                .toList();
    }
}
