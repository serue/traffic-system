package com.beymo.traffic.station.model;

import jakarta.persistence.*;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 1)
    private Long id;
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Station(Long id, String code, String name, District district) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.district = district;
    }

    public Station() {}

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", district=" + district +
                '}';
    }
}
