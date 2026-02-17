package ru.fsog.tmhHacaton2026.util;

import lombok.experimental.UtilityClass;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.entity.Defect;

@UtilityClass
public class DefectMapper {
    public DefectDetailsDTO convertToDto(Defect defect) {
        DefectDetailsDTO defectDetailsDTO = new DefectDetailsDTO();
        defectDetailsDTO.setId(defect.getId());
        defectDetailsDTO.setTypeName(defect.getDefectTypes().getName());
        defectDetailsDTO.setComment(defect.getComment());
        defectDetailsDTO.setLine(defect.getLine());
        defectDetailsDTO.setStatus(defect.isStatus());
        if (defect.getPhoto() != null) {
            defectDetailsDTO.setPhotoId(defect.getPhoto().getId());
        }
        return defectDetailsDTO;
    }
}
