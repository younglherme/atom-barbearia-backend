package dev.guilhermesilva.atom_backend.repository;

import dev.guilhermesilva.atom_backend.entity.Agendamento;
import dev.guilhermesilva.atom_backend.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByBarbeiroIdAndDataHoraInicioBetweenOrderByDataHoraInicioAsc(
            Long barbeiroId,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    boolean existsByBarbeiroIdAndStatusAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            Long barbeiroId,
            StatusAgendamento status,
            LocalDateTime novoFim,
            LocalDateTime novoInicio
    );
}