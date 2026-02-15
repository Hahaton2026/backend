package ru.fsog.tmhHacaton2026.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class DefectDetailsDTO {
    private long id;
    private String typeName;
    private String comment;
    private String line;
    private long photoId;
    private Double latitude;
    private Double longitude;
}