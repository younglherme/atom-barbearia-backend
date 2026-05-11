package dev.guilhermesilva.atom_backend.controller;

import dev.guilhermesilva.atom_backend.dto.request.AppointmentRequest;
import dev.guilhermesilva.atom_backend.dto.response.AppointmentResponse;
import dev.guilhermesilva.atom_backend.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Gerenciamento de agendamentos de serviços")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Cria um novo agendamento")
    @PostMapping
    public ResponseEntity<AppointmentResponse> create(
            @Valid @RequestBody AppointmentRequest request
    ) {
        AppointmentResponse response = appointmentService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Lista todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAll() {
        List<AppointmentResponse> response = appointmentService.findAll();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca agendamentos por data")
    @GetMapping("/date")
    public ResponseEntity<List<AppointmentResponse>> findByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        List<AppointmentResponse> response = appointmentService.findByDate(date);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Busca agendamentos marcados por data")
    @GetMapping("/date/scheduled")
    public ResponseEntity<List<AppointmentResponse>> findScheduledByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        List<AppointmentResponse> response = appointmentService.findScheduledByDate(date);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancela um agendamento pelo ID")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancel(
            @PathVariable Long id
    ) {
        AppointmentResponse response = appointmentService.cancel(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Conclui um agendamento pelo ID")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<AppointmentResponse> complete(
            @PathVariable Long id
    ) {
        AppointmentResponse response = appointmentService.complete(id);

        return ResponseEntity.ok(response);
    }
}