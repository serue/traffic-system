package com.beymo.traffic.accused.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Licence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licence_seq")
    @SequenceGenerator(name = "licence_seq", sequenceName = "licence_seq", allocationSize = 1)
    private Long id;
    private String number;
    private LocalDate firstIssueDate;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String placeOfIssue;
    @ManyToOne
    @JoinColumn(name = "accused_id", nullable = false)
    private Accused accused;

    public Licence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getFirstIssueDate() {
        return firstIssueDate;
    }

    public void setFirstIssueDate(LocalDate firstIssueDate) {
        this.firstIssueDate = firstIssueDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public Accused getAccused() {
        return accused;
    }

    public void setAccused(Accused accused) {
        this.accused = accused;
    }
}
