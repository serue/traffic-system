package com.beymo.traffic.notice.model;

import com.beymo.traffic.court.model.Court;
import com.beymo.traffic.offence.model.Offence;
import com.beymo.traffic.shared.Receipt;
import com.beymo.traffic.station.model.Province;
import com.beymo.traffic.station.model.Station;
import com.beymo.traffic.vehicle.Vehicle;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
public class Notice extends Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice_seq")
    @SequenceGenerator(name = "notice_seq", sequenceName = "notice_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToMany
    @JoinTable(
            name = "notice_offence",  // Name of the join table
            joinColumns = @JoinColumn(name = "notice_id"),  // Column in the join table for Receipt
            inverseJoinColumns = @JoinColumn(name = "offence_id")  // Column in the join table for Offence
    )
    private Set<Offence> offences;
    private String crb;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Enumerated(EnumType.STRING)
    private NoticeStatus status;

    public Notice() {
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

    public Set<Offence> getOffences() {
        return offences;
    }

    public void setOffences(Set<Offence> offences) {
        this.offences = offences;
    }

    public NoticeStatus getStatus() {
        return status;
    }

    public void setStatus(NoticeStatus status) {
        this.status = status;
    }

    public String getCrb() {
        return crb;
    }

    public void setCrb(String crb) {
        this.crb = crb;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station=station;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", court=" + court +
                ", offences=" + offences +
                ", crb='" + crb + '\'' +
                ", station=" + station +
                ", status=" + status +
                '}';
    }
}
