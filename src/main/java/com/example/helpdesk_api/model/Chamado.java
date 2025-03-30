package com.example.helpdesk_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "chamado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Status status;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private Cliente cliente;
    private Tecnico tecnico;
}
