package com.beymo.traffic.court.exception;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException(String message) {
        super(message);
    }
}
