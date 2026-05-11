package dev.guilhermesilva.atom_backend.repository;

import dev.guilhermesilva.atom_backend.entity.TipoCorte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoCorteRepository extends JpaRepository<TipoCorte, Long> {

    List<TipoCorte> findByAtivoTrue();
}