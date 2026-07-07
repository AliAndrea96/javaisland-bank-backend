package com.javaisland.bank_backend.request;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplyDTO {

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long userId;

    @NotNull(message = "L'importo è obbligatorio")
    @DecimalMin(value = "0.01", message = "L'importo deve essere maggiore di zero")
    private BigDecimal amount;

    @NotNull(message = "Il tasso d'interesse è obbligatorio")
    @DecimalMin(value = "0.00", message = "Il tasso non può essere negativo")
    private BigDecimal annualRate;

    @NotNull(message = "La durata in mesi è obbligatoria")
    @Min(value = 1, message = "La durata deve essere di almeno 1 mese")
    private Integer months;

    @NotNull(message = "L'ID del conto per l'accredito è obbligatorio")
    private Long accountId;
}