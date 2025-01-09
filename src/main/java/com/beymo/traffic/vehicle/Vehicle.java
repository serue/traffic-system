package com.beymo.traffic.vehicle;

import jakarta.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name = "vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 1)
    private Long id;
    private String registration;
    private String model;
    private String color;
    @ManyToOne
    @JoinColumn(
            name = "category_id"
    )
    private Category category;

    public Vehicle(Long id, String registration, String model, String color, Category category) {
        this.id = id;
        this.registration = registration;
        this.model = model;
        this.color = color;
        this.category = category;
    }
    public Vehicle() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", category=" + category +
                '}';
    }
}
