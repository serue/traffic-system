package com.beymo.traffic.court.model;

import com.beymo.traffic.shared.Auditable;
import com.beymo.traffic.station.model.District;
import jakarta.persistence.*;


@Entity
public class Court extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "court_seq")
    @SequenceGenerator(name = "court_seq", sequenceName = "court_seq", allocationSize = 1)
    private Long id;
    private String courtNumber;
    private String name;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;


    public Court() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourtNumber() {
        return courtNumber;
    }

    public void setCourt(String court) {
        this.courtNumber = court;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }



    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", court='" + courtNumber + '\'' +
                ", name='" + name + '\'' +
                ", district=" + district +
                '}';
    }
}
