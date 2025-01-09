package com.beymo.traffic.notice.model;

import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.shared.Receipt;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
public class Admission  extends Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_seq")
    @SequenceGenerator(name = "admission_seq", sequenceName = "admission_seq", allocationSize = 1)
    private Long id;
    private String noticeReference;
    private String witness;
    @ManyToMany
    @JoinTable(
            name = "admission_offence",  // Name of the join table
            joinColumns = @JoinColumn(name = "admission_id"),  // Column in the join table for Receipt
            inverseJoinColumns = @JoinColumn(name = "offence_id")  // Column in the join table for Offence
    )
    private Set<Offence> offences;
    private LocalDate licenceSurrenderDate;

    public Admission() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLicenceSurrenderDate() {
        return licenceSurrenderDate;
    }

    public void setLicenceSurrenderDate(LocalDate licenceSurrenderDate) {
        this.licenceSurrenderDate = licenceSurrenderDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeReference() {
        return noticeReference;
    }

    public void setNoticeReference(String noticeReference) {
        this.noticeReference = noticeReference;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public Set<Offence> getOffences() {
        return offences;
    }

    public void setOffences(Set<Offence> offences) {
        this.offences = offences;
    }

    @Override
    public String toString() {
        return "Admission{" +
                "id=" + id +
                ", noticeReference='" + noticeReference + '\'' +
                ", witness='" + witness + '\'' +
                ", offences=" + offences +
                ", licenceSurrenderDate=" + licenceSurrenderDate +
                '}';
    }
}
