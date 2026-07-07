package com.javaisland.bank_backend.user;

import lombok.Getter; // 📌 Importiamo il Getter di Lombok

@Getter // 📌 Genera automaticamente il metodo getStatusName()
public enum UserStatus {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    ANNULED("ANNULED"),
    SUSPENDED("SUSPENDED");

    private final String statusName;

    UserStatus(String statusName) {
        this.statusName = statusName;
    }
}