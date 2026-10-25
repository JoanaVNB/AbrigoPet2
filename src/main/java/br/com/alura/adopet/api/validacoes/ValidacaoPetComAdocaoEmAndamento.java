package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
//        List<Adocao> adocoes = repository.findAll(); //carrega adocoes e joga numa lista
//        //nao é uma boa pratica pois é ruim para a performace carregar todas as adocoes para a memoria
//        Pet pet = petRepository.getReferenceById(dto.idPet()); //carrego o pet que esta sendo feita a solicitacao de adoca
//        for (Adocao a : adocoes) {//percorro a lista de adocao para verificar se este ped esta na lista de aguardando avaliacao
//            if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
//                throw new ValidationException("Pet já está aguardando avaliação para ser adotado!");
//
//            }
//        }

        boolean petTemAdocaoEmAndamento = repository.existsByPetAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (petTemAdocaoEmAndamento) {
            throw new ValidationException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}