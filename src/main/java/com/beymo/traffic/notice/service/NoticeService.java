package com.beymo.traffic.notice.service;

import com.beymo.traffic.notice.dto.NoticeRequest;

public interface NoticeService {
    String createNotice(NoticeRequest request);
}
