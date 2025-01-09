package com.beymo.traffic.offence.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class Offence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offence_seq")
    @SequenceGenerator(name = "offence_seq", sequenceName = "offence_seq", allocationSize = 1)
    private Long id;
    private String section;
    private String title;
    private Double fine;

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Offence(Long id, String section, String title, Double fine) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.fine = fine;
    }
    public Offence() {

    }

    @Override
    public String toString() {
        return "Offence{" +
                "id=" + id +
                ", section='" + section + '\'' +
                ", title='" + title + '\'' +
                ", fine=" + fine +
                '}';
    }
}
