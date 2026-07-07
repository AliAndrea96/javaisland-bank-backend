package com.javaisland.bank_backend.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountClosureDTO {
    @NotNull(message = "L'ID del conto è obbligatorio")
    private Long accountId;

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long userId;
}