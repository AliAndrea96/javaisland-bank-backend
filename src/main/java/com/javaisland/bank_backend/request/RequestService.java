package com.javaisland.bank_backend.request;

import com.javaisland.bank_backend.exception.ApiBankException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RequestService {

    // 📌 1. Costante per evitare la duplicazione della stringa "PENDING"
    private static final String STATUS_PENDING = "PENDING";

    private final BankRequestRepository requestRepository;
    private final LoanRepository loanRepository;

    public RequestService(BankRequestRepository requestRepository, LoanRepository loanRepository) {
        this.requestRepository = requestRepository;
        this.loanRepository = loanRepository;
    }

    // 🧠 1. CREA UNA RICHIESTA BUROCRATICA GENERICA
    public BankRequest createRequest(Long userId, String description) {
        BankRequest request = new BankRequest();
        request.setUserId(userId);
        request.setDescription(description);
        request.setStatus(STATUS_PENDING); // Usa la costante
        return requestRepository.save(request);
    }

    // 🧠 2. APPROVAZIONE O RIFIUTO DELLA RICHIESTA DA PARTE DI UN DIPENDENTE
    public BankRequest reviewRequest(Long requestId, Long employeeId, String status, String rejectionReason) {
        BankRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new ApiBankException("Richiesta burocratica non trovata."));

        if (!STATUS_PENDING.equals(request.getStatus())) { // Usa la costante
            throw new ApiBankException("Questa richiesta è già stata elaborata.");
        }

        request.setStatus(status);
        request.setReviewedByUserId(employeeId);

        if ("REJECTED".equals(status)) {
            if (rejectionReason == null || rejectionReason.trim().isEmpty()) {
                throw new ApiBankException("È obbligatorio inserire il motivo del rifiuto.");
            }
            request.setRejectionReason(rejectionReason);
        }

        return requestRepository.save(request);
    }

    // 🧠 3. CALCOLO RATA E RICHIESTA FINANZIAMENTO (LOAN)
// 🧠 3. CALCOLO RATA E RICHIESTA FINANZIAMENTO (Aggiornato con accountId)
    public Loan applyForLoan(Long userId, BigDecimal amount, BigDecimal annualRate, Integer months, Long accountId) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || months <= 0) {
            throw new ApiBankException("Importo e durata del finanziamento devono essere maggiori di zero.");
        }

        BigDecimal monthlyInstallment = calculateMonthlyInstallment(amount, annualRate, months);

        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setLoanAmount(amount);
        loan.setInterestRate(annualRate);
        loan.setDurationMonths(months);
        loan.setMonthlyInstallment(monthlyInstallment);
        loan.setStatus(STATUS_PENDING);
        loan.setAccountId(accountId); // 📌 Imposta il conto corrente inserito nel DTO

        return loanRepository.save(loan);
    }

    // 📌 2. Metodo estratto per il calcolo matematico della rata
    private BigDecimal calculateMonthlyInstallment(BigDecimal amount, BigDecimal annualRate, Integer months) {
        BigDecimal years = BigDecimal.valueOf(months).divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_UP);
        BigDecimal totalInterest = amount.multiply(annualRate.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)).multiply(years);
        BigDecimal totalToRepay = amount.add(totalInterest);
        return totalToRepay.divide(BigDecimal.valueOf(months), 2, RoundingMode.HALF_UP);
    }
}