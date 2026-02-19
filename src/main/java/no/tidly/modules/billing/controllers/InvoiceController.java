package no.tidly.modules.billing.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import no.tidly.modules.billing.dto.InvoiceResponse;
import no.tidly.modules.billing.usecase.invoice.GetInvoicesUseCase;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
@Tag(name = "Billing - Invoices", description = "Endpoints for managing subscription invoices")
public class InvoiceController {

    private final GetInvoicesUseCase getInvoicesUseCase;

    @GetMapping("/company/{companyId}")
    @Operation(summary = "Get all invoices for a company's active subscription")
    public ResponseEntity<List<InvoiceResponse>> getInvoices(@PathVariable UUID companyId) {
        return ResponseEntity.ok(getInvoicesUseCase.execute(companyId));
    }
}
