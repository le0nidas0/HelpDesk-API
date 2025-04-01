package com.example.helpdesk_api.service;

import com.example.helpdesk_api.model.Cliente;
import com.example.helpdesk_api.model.Tecnico;
import com.example.helpdesk_api.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public List<Tecnico> listarTodos(){
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> buscarPorId(Long id){
        return tecnicoRepository.findById(id);
    }

    public Tecnico cadastrar(Tecnico tecnico){
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico atualizar(Long id, Tecnico novoTecnico){
        Tecnico tecnicoExistente = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado!"));

        tecnicoExistente.setNome(novoTecnico.getNome());
        tecnicoExistente.setEmail(novoTecnico.getEmail());
        tecnicoExistente.setTelefone(novoTecnico.getTelefone());
        tecnicoExistente.setChamadosResolvidos(novoTecnico.getChamadosResolvidos());
        tecnicoExistente.setEspecialidade(novoTecnico.getEspecialidade());
        tecnicoExistente.setSenha(novoTecnico.getSenha());

        return tecnicoRepository.save(tecnicoExistente);
    }

    public void deletar(Long id){
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado!"));
        tecnicoRepository.delete(tecnico);
    }
}
