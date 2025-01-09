package com.beymo.traffic.accused.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
public class Accused {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accused_seq")
    @SequenceGenerator(name = "accused_seq", sequenceName = "accused_seq", allocationSize = 1)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String businessAddress;
    private String city;
    private String birthplace;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String country;
    // the date the accused entered into the country if not a citizen
    private LocalDate entryDate;
    @Enumerated(EnumType.STRING)
    private PortOfEntry port;
    @OneToMany(mappedBy = "accused", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Licence> licences;


    public Accused() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entrydate) {
        this.entryDate = entrydate;
    }

    public PortOfEntry getPort() {
        return port;
    }

    public void setPort(PortOfEntry port) {
        this.port = port;
    }

    public Set<Licence> getLicences() {
        return licences;
    }

    public void setLicences(Set<Licence> licences) {
        this.licences = licences;
    }

    @Override
    public String toString() {
        return "Accused{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", city='" + city + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", entrydate=" + entryDate +
                ", port=" + port +
                ", licences=" + licences +
                '}';
    }
}
