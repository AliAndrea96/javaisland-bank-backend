package com.javaisland.bank_backend.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardBlockDTO {
    @NotNull(message = "L'ID della carta è obbligatorio")
    private Long cardId;

    @NotNull(message = "L'ID utente è obbligatorio")
    private Long userId;
}