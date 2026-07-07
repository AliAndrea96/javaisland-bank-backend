package com.javaisland.bank_backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequestDTO {

    @NotNull(message = "L'ID della richiesta è obbligatorio")
    private Long requestId;

    @NotBlank(message = "Lo stato di approvazione è obbligatorio")
    private String status;

    private String rejectionReason;

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private Long employeeId;

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long userId;
}