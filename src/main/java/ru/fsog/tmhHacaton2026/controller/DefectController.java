package ru.fsog.tmhHacaton2026.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.entity.Defect;
import ru.fsog.tmhHacaton2026.service.DefectService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DefectDetailsDTO> uploadDefectData(
            @RequestPart("file") MultipartFile file,
            @RequestPart("data") DefectDetailsDTO data) throws IOException {

        return ResponseEntity.ok(defectService.createDefect(file, data));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DefectDetailsDTO>> getAllDefectsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok((Page<DefectDetailsDTO>) defectService.getAllDefects(pageable));
    }
}
