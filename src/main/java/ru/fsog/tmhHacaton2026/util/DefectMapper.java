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
        if (defect.getPhoto() != null) {
            defectDetailsDTO.setPhotoId(defect.getPhoto().getId());
        }
        defectDetailsDTO.setLatitude(defect.getLatitude());
        defectDetailsDTO.setLongitude(defect.getLongitude());
        return defectDetailsDTO;
    }
}
