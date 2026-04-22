package com.ou.ailearning.entity;

import com.ou.ailearning.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "appointment_id")
    private int appointmentId;
    private Double amount;        // số tiền
    private String transactionId; // mã giao dịch từ VNPAY

    public Payment() {
    }
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;        // SUCCESS / FAILED

    public Payment(int id, int appointmentId, Double amount, String transactionId, PaymentStatus status, LocalDateTime paidAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.status = status;
        this.paidAt = paidAt;
    }

    private LocalDateTime paidAt; // thời điểm thanh toán

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
}
