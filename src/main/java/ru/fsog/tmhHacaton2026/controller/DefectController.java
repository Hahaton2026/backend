package ru.fsog.tmhHacaton2026.controller;


//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.fsog.tmhHacaton2026.dto.CordsDTO;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.dto.DefectTypesDTO;
import ru.fsog.tmhHacaton2026.service.DefectService;
import ru.fsog.tmhHacaton2026.service.DefectTypeService;
import ru.fsog.tmhHacaton2026.service.MetroLineResolverService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;
    private final MetroLineResolverService metroLineResolverService;
    private final DefectTypeService defectTypeService;



    @PostMapping("/upload/cords")
    public ResponseEntity<String> detectLines(
            @RequestBody CordsDTO cords
            ) {
        return ResponseEntity.ok(metroLineResolverService.getLineByCoords(cords.getLatitude(), cords.getLongitude()));
    }
    @PostMapping(value = "/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> sendPhoto(
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(defectService.savePhoto(file));
    }

    @PostMapping("/upload/description")
    public ResponseEntity<DefectDetailsDTO> sendDescription(
            @RequestBody DefectDetailsDTO data
    ) {
        return ResponseEntity.ok(defectService.saveDescriptionOfPhoto(data));

    }

    @GetMapping("/info/defects")
    public ResponseEntity<Page<DefectDetailsDTO>> getAllDefectsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(defectService.getAllDefectsPaginated(pageable));
    }

    @GetMapping("/info/allTypes")
    public ResponseEntity<List<DefectTypesDTO>> getAllDefectsTypesPaginated() {
        return ResponseEntity.ok(defectTypeService.getAllDefectTypes());
    }

    @PostMapping("/add-defect-types")
    public ResponseEntity<String> addDefectType(
            @RequestBody DefectTypesDTO defectTypesDTO
    ) {
        if (defectTypesDTO.getName() == null || defectTypesDTO.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Название типа не может быть пустым");
        }

        defectService.addDefectType(defectTypesDTO);
        return ResponseEntity.ok("Новый тип дефекта успешно добавлен");
    }
}

