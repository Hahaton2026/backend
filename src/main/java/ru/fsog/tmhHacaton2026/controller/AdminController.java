package ru.fsog.tmhHacaton2026.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fsog.tmhHacaton2026.dto.DefectDetailsDTO;
import ru.fsog.tmhHacaton2026.service.DefectService;

import java.util.List;
@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DefectService defectService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefect(@PathVariable Long id) {
        defectService.deleteDefect(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/new-defects")
    public ResponseEntity<List<DefectDetailsDTO>> getAllNewDefects(){
        return ResponseEntity.ok(defectService.getAllNewDefects());
    }

    @PatchMapping("/{id}/fix")
    public ResponseEntity<Void> markAsFixed(@PathVariable long id) {
        defectService.markAsFixed(id);
        return ResponseEntity.noContent().build();
    }
}
