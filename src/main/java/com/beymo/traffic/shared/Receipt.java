package com.beymo.traffic.shared;

import com.beymo.traffic.accused.model.Accused;
import com.beymo.traffic.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Receipt {

    @ManyToOne
    @JoinColumn(name = "accused_id")
    private Accused accused;
    private Double paidAmount;
    private LocalDateTime datePaid;
    private String z69j;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User issuedBy;
    private String signature;

    @CreatedDate
    @Column(nullable = false, insertable = true, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(nullable = true, insertable = false, updatable = true)
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(nullable = false, insertable = true, updatable = false)
    private Integer createdBy;
    @LastModifiedBy
    @Column(nullable = false, insertable = true, updatable = false)
    private Integer lastModifiedBy;

    public Receipt() {
    }

    public Accused getAccused() {
        return accused;
    }

    public void setAccused(Accused accused) {
        this.accused = accused;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDateTime getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDateTime datePaid) {
        this.datePaid = datePaid;
    }

    public String getZ69j() {
        return z69j;
    }

    public void setZ69j(String z69j) {
        this.z69j = z69j;
    }

    public User getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(User issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
