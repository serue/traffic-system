package com.beymo.traffic.notice.controller;

import com.beymo.traffic.notice.dto.NoticeRequest;
import com.beymo.traffic.notice.dto.NoticeResponse;
import com.beymo.traffic.notice.model.Notice;
import com.beymo.traffic.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/form265")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'STAFF', 'SUPERVISOR')")
    @PostMapping("/issue-notice")
    public ResponseEntity<String> createNotice(@RequestBody @Validated NoticeRequest request) {
        ;
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.createNotice(request));
    }
}
