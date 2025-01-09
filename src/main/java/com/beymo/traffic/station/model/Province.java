package com.beymo.traffic.station.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 1)
    private Long id;
    private String code;
    private String name;

    @OneToMany(mappedBy = "province", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<District> districts;


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

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Province(Long id, String code, String name, List<District> districts) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.districts = districts;
    }

    public Province(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Province() {
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", districts=" + districts +
                '}';
    }
}
