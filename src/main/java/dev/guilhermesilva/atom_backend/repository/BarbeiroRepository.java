package dev.guilhermesilva.atom_backend.repository;

import dev.guilhermesilva.atom_backend.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    Optional<Barbeiro> findByEmail(String email);
}