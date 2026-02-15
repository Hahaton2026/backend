package ru.fsog.tmhHacaton2026.service;

import ru.fsog.tmhHacaton2026.dto.DefectTypesDTO;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;

import java.util.List;

public interface DefectTypeService {

    List<DefectTypesDTO> getAllDefectTypes();
}
