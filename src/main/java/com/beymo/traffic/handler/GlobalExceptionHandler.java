package com.beymo.traffic.handler;

import com.beymo.traffic.auth.BootstrapUser;
import com.beymo.traffic.court.exception.CourtNotFoundException;
import com.beymo.traffic.offence.exception.OffenceNotFoundException;
import com.beymo.traffic.role.RoleException;
import com.beymo.traffic.station.exception.DistrictNotFoundException;
import com.beymo.traffic.station.exception.ProvinceNotFountException;
import com.beymo.traffic.station.exception.StationNotFoundException;
import com.beymo.traffic.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.ObjectError;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(StationNotFoundException.class)
    public ResponseEntity<String> handler(StationNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }

    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity<String> handler(DistrictNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }
    @ExceptionHandler(ProvinceNotFountException.class)
    public ResponseEntity<String> handler(ProvinceNotFountException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<String> handler(RoleException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        ErrorResponse errorResponse = new ErrorResponse();
        exp.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        errorResponse.setValidationErrors(errors);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        errorResponse
                );
    }

    @ExceptionHandler(OffenceNotFoundException.class)
    public ResponseEntity<String> handler(OffenceNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }
    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<String> handler(CourtNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handler(UserNotFoundException exp) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
    }


}
