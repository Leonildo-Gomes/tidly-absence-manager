package no.tidly.modules.configuration.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.configuration.dto.BalanceTransactionRequest;
import no.tidly.modules.configuration.dto.BalanceTransactionResponse;
import no.tidly.modules.configuration.usecase.balancetransaction.CreateBalanceTransactionUseCase;
import no.tidly.modules.configuration.usecase.balancetransaction.DeleteBalanceTransactionUseCase;
import no.tidly.modules.configuration.usecase.balancetransaction.GetBalanceTransactionByIdUseCase;
import no.tidly.modules.configuration.usecase.balancetransaction.GetBalanceTransactionsByEmployeeUseCase;
import no.tidly.modules.configuration.usecase.balancetransaction.UpdateBalanceTransactionUseCase;

@RestController
@RequestMapping("/api/v1/balance-transactions")
@RequiredArgsConstructor
public class BalanceTransactionController {

    private final CreateBalanceTransactionUseCase createBalanceTransactionUseCase;
    private final GetBalanceTransactionByIdUseCase getBalanceTransactionByIdUseCase;
    private final GetBalanceTransactionsByEmployeeUseCase getBalanceTransactionsByEmployeeUseCase;
    private final UpdateBalanceTransactionUseCase updateBalanceTransactionUseCase;
    private final DeleteBalanceTransactionUseCase deleteBalanceTransactionUseCase;

    @PostMapping
    public ResponseEntity<BalanceTransactionResponse> create(@Valid @RequestBody BalanceTransactionRequest request) {
        BalanceTransactionResponse response = createBalanceTransactionUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalanceTransactionResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(getBalanceTransactionByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<BalanceTransactionResponse>> getByEmployee(@RequestParam UUID employeeId) {
        return ResponseEntity.ok(getBalanceTransactionsByEmployeeUseCase.execute(employeeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalanceTransactionResponse> update(@PathVariable UUID id,
            @Valid @RequestBody BalanceTransactionRequest request) {
        return ResponseEntity.ok(updateBalanceTransactionUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteBalanceTransactionUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
