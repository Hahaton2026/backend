package ru.fsog.tmhHacaton2026.util;

import lombok.experimental.UtilityClass;
import ru.fsog.tmhHacaton2026.dto.DefectTypesDTO;
import ru.fsog.tmhHacaton2026.entity.DefectTypes;

@UtilityClass
public class DefectTypesMapper {

    public DefectTypesDTO convertToDto(DefectTypes defectTypes) {
        DefectTypesDTO defectTypesDTO = new DefectTypesDTO();
        defectTypesDTO.setId(defectTypes.getId());
        defectTypesDTO.setName(defectTypes.getName());
        return defectTypesDTO;
    }


}
