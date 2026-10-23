package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;


    public void solicitar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

       //chmar validacoes

        Adocao adocao = new Adocao();
        adocao.setData(LocalDateTime.now());
        adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);
        repository.save(adocao);

        emailService.enviarEmail(adocao.getPet().getAbrigo().getEmail(), "Solicitação de adoção", "Olá " + adocao.getPet().getAbrigo().getNome() + "!\n\nUma solicitação de adoção foi registrada hoje para o pet: " +adocao.getPet().getNome() +". \nFavor avaliar para aprovação ou reprovação."););

    }

    public void aprovar (AprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus(StatusAdocao.APROVADO);
    }

    public void reprovar (ReprovacaoAdocaoDto dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.setStatus (StatusAdocao.REPROVADO);
        adocao.setJustificativaStatus (dto.justificativa());
    }
    }
}
