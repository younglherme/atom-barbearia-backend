package dev.guilhermesilva.atom_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tipos_corte")
public class TipoCorte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Integer duracaoEmMinutos;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}