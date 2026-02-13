package ru.fsog.tmhHacaton2026.dto;

import lombok.Data;

import java.util.List;

@Data
public class DefectRequestDTO {
    private List<DefectDetailsDTO> defects;
}
