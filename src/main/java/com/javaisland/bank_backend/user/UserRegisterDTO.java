package com.javaisland.bank_backend.user;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "Il Keycloak ID è obbligatorio")
    private String keycloakId;

    @NotBlank(message = "Lo username non può essere vuoto")
    @Size(min = 4, max = 50, message = "Lo username deve avere tra 4 e 50 caratteri")
    private String username;

    @NotBlank(message = "Il nome è obbligatorio")
    private String firstName;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String lastName;

    @NotNull(message = "La data di nascita è obbligatoria")
    @Past(message = "La data di nascita deve essere una data passata")
    private LocalDate birthDate;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Inserisci un indirizzo email formattato correttamente")
    private String email;

    private String branchCode;
}