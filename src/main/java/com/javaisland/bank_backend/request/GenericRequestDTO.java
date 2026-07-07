package com.javaisland.bank_backend.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericRequestDTO {

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long userId;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(max = 255, message = "La descrizione non può superare i 255 caratteri")
    private String description;
}