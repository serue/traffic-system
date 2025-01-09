package com.beymo.traffic.station.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 1)
    private Long id;
    private String code;
    private String name;
    @ManyToOne
    @JoinColumn(
            name = "province_id"
    )
    private Province province;
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Station> stations;


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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public District(Long id, String code, String name, Province province, List<Station> stations) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.province = province;
        this.stations = stations;
    }

    public District(Long id, String code, String name, Province province) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.province = province;
    }

    public District() {}

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", province=" + province +
                ", stations=" + stations +
                '}';
    }

}
