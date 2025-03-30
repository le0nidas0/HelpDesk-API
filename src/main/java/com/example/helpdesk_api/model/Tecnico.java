package com.example.helpdesk_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tecnico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico extends Usuario{
    private String especialidade;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL)
    private List<Chamado> chamadosResolvidos;
}
