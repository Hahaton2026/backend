package ru.fsog.tmhHacaton2026.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.fsog.tmhHacaton2026.exception.DefectTypesNotFoundException;

@ControllerAdvice
public class GlovalExceptionHandler {

    @ExceptionHandler(DefectTypesNotFoundException.class)
    public ResponseEntity<String> handlerDefectTypesNotFoundException(DefectTypesNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
