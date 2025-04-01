package com.example.helpdesk_api.service;

import com.example.helpdesk_api.model.Cliente;
import com.example.helpdesk_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> clientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente novoCliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        clienteExistente.setNome(novoCliente.getNome());
        clienteExistente.setEmail(novoCliente.getEmail());
        clienteExistente.setTelefone(novoCliente.getTelefone());

        return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        clienteRepository.delete(cliente);
    }
}
