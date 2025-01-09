package com.beymo.traffic.court.model;

import com.beymo.traffic.shared.Auditable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "_schedules")
public class CourtSchedule extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_seq")
    @SequenceGenerator(name = "court_seq", sequenceName = "court_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;
    private LocalDate startDate;
    private LocalDate endDate;

    public CourtSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CourtSchedule{" +
                "id=" + id +
                ", court=" + court +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
