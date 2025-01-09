package com.beymo.traffic.notice.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Table(name = "warrant")
public class ArrestWarrant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq")
    @SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "notice_id"
    )
    private Notice notice;
    private LocalDate issuedDate;
    private WarrantStatus status;

    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
}
