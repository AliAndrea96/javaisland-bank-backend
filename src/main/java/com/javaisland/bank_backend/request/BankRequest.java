package com.javaisland.bank_backend.request;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "requests")
@Getter
@Setter
public class BankRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "rejection_reason", length = 255)
    private String rejectionReason;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "reviewed_by_user_id")
    private Long reviewedByUserId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Costruttore vuoto manuale (sostituisce @NoArgsConstructor ed evita avvisi ridondanti)
    public BankRequest() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(ZoneId.of("Europe/Rome"));
        }
    }
}