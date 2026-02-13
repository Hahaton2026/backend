package ru.fsog.tmhHacaton2026.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DefectResponseDTO {
    private Long photoId;
    private LocalDateTime createdAt;
    private String status;
}
