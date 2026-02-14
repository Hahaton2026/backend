package ru.fsog.tmhHacaton2026.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.coyote.Response;
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

//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @RequestBody(content = @Content(encoding = @Encoding(name = "personDTO", contentType = "application/json")))
//    public ResponseEntity<DefectDetailsDTO> uploadDefectData(
//            @RequestPart("file") MultipartFile file,
//            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
//            @RequestPart("data") String dataJson) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
//        DefectDetailsDTO data = objectMapper.readValue(dataJson, DefectDetailsDTO.class);
//        return ResponseEntity.ok(defectService.createDefect(file, data));
//    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<DefectDetailsDTO>> getAllDefectsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(defectService.getAllDefectsPaginated(pageable));
    }


    @PostMapping(value = "/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> sendPhoto(
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(defectService.savePhoto(file));
    }

    @PostMapping("/upload/description")
    public ResponseEntity sendDescription(
            @RequestBody DefectDetailsDTO data
    ) {
        return ResponseEntity.ok(defectService.saveDescriptionOfPhoto(data));

    }
}
