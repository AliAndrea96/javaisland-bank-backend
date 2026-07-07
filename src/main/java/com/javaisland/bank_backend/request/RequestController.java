package com.javaisland.bank_backend.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // 🧠 1. Creazione richiesta generica (Solo Clienti)
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BankRequest> createRequest(@Valid @RequestBody GenericRequestDTO dto) {
        BankRequest newRequest = requestService.createRequest(dto.getUserId(), dto.getDescription());
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    // 🧠 2. Richiesta chiusura conto (Solo Clienti)
    @PostMapping("/closure")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BankRequest> requestAccountClosure(@Valid @RequestBody AccountClosureDTO dto) {
        BankRequest newRequest = requestService.createAccountClosureRequest(dto.getUserId(), dto.getAccountId());
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    // 🧠 3. Richiesta blocco carta (Solo Clienti)
    @PostMapping("/card-block")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<BankRequest> requestCardBlock(@Valid @RequestBody CardBlockDTO dto) {
        BankRequest newRequest = requestService.createCardBlockRequest(dto.getUserId(), dto.getCardId());
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }

    // 🧠 4. Richiesta di un prestito (Solo Clienti)
    @PostMapping("/loans")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Loan> applyForLoan(@Valid @RequestBody LoanApplyDTO dto) {
        Loan newLoan = requestService.applyForLoan(
                dto.getUserId(),
                dto.getAmount(),
                dto.getAnnualRate(),
                dto.getMonths(),
                dto.getAccountId()
        );
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    // 🧠 5. Recensione rapida tramite parametri URL (Solo Dipendenti)
    @PatchMapping("/{requestId}/review")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<BankRequest> reviewRequest(
            @PathVariable Long requestId,
            @RequestParam Long employeeId,
            @RequestParam String status,
            @RequestParam(required = false) String rejectionReason) {

        BankRequest reviewedRequest = requestService.reviewRequest(requestId, employeeId, status, rejectionReason);
        return ResponseEntity.ok(reviewedRequest);
    }

    // 🧠 6. Processamento avanzato con Corpo DTO (Solo Dipendenti)
    @PostMapping("/process")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<BankRequest> processRequest(@Valid @RequestBody ProcessRequestDTO dto) {
        BankRequest processedRequest = requestService.processBankRequest(dto);
        return ResponseEntity.ok(processedRequest);
    }
}