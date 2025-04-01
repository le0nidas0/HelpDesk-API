package com.example.helpdesk_api.service;

import com.example.helpdesk_api.model.Chamado;
import com.example.helpdesk_api.model.Status;
import com.example.helpdesk_api.model.Tecnico;
import com.example.helpdesk_api.repository.ChamadoRepository;
import com.example.helpdesk_api.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final TecnicoRepository tecnicoRepository;
    private final EmailService emailService;

    public List<Chamado> listarTodos(){
        return chamadoRepository.findAll();
    }

    public Optional<Chamado> buscarPorId(Long id){
        return chamadoRepository.findById(id);
    }

    public Chamado abrirChamado(Chamado chamado) {
        chamado.setDataAbertura(LocalDate.now());
        chamado.setStatus(Status.ABERTO);

        Chamado chamadoSalvo = chamadoRepository.save(chamado);

        Tecnico tecnico = chamado.getTecnico();
        if (tecnico != null && tecnico.getEmail() != null) {
            String assunto = "Novo Chamado Atribuído";
            String mensagem = String.format(
                    "Olá %s, um novo chamado foi atribuído a você! <br><br>" +
                            "<strong>Título:</strong> %s <br>" +
                            "<strong>Descrição:</strong> %s <br>" +
                            "<strong>Prioridade:</strong> %s <br>" +
                            "<strong>Status:</strong> %s <br><br>" +
                            "Acesse o sistema para mais detalhes.",
                    tecnico.getNome(), chamado.getTitulo(), chamado.getDescricao(),
                    chamado.getPrioridade(), chamado.getStatus()
            );

            emailService.enviarEmail(tecnico.getEmail(), assunto, mensagem);
        }

        return chamadoSalvo;
    }

    public Chamado atualizarStatus(Long id, Status status){
        Chamado chamadoExistente = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        chamadoExistente.setStatus(status);
        return chamadoRepository.save(chamadoExistente);
    }

    public void deletar(Long id){
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        chamadoRepository.delete(chamado);
    }

}
