package dev.guilhermesilva.atom_backend.controller;

import dev.guilhermesilva.atom_backend.dto.request.ServiceTypeRequest;
import dev.guilhermesilva.atom_backend.dto.response.ServiceTypeResponse;
import dev.guilhermesilva.atom_backend.service.ServiceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/service-types")
@RestController
@RequiredArgsConstructor
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @Operation(summary = "Create a new service type")
    @PostMapping
    public ResponseEntity<ServiceTypeResponse> create(
            @Valid @RequestBody ServiceTypeRequest request
    ) {
        ServiceTypeResponse response = serviceTypeService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Find all service types")
    @GetMapping
    public ResponseEntity<List<ServiceTypeResponse>> findAll() {
        List<ServiceTypeResponse> response = serviceTypeService.findAll();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find all active service types")
    @GetMapping("/active")
    public ResponseEntity<List<ServiceTypeResponse>> findAllActive() {
        List<ServiceTypeResponse> response = serviceTypeService.findAllActive();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find service type by id")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> findById(
            @PathVariable Long id
    ) {
        ServiceTypeResponse response = serviceTypeService.findById(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update service type by id")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceTypeResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ServiceTypeRequest request
    ) {
        ServiceTypeResponse response = serviceTypeService.update(id, request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deactivate service type by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id
    ) {
        serviceTypeService.deactivate(id);

        return ResponseEntity.noContent().build();
    }
}