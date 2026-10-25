package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;


    public void cadastrar(CadastroAbrigoDto dto){
        Abrigo abrigo = abrigoRepository.getReferenceById(dto.id());
        abrigoRepository.save(abrigo);
    }

    public List<Pet> listarPets(String idOuNome){
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = abrigoRepository.getReferenceById(id).getPets();
            return pets;
        } catch (EntityNotFoundException enfe) {
            return null;
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = abrigoRepository.findByNome(idOuNome).getPets();
                return pets;
            } catch (EntityNotFoundException enfe) {
                return null;
            }
        }
    }

    public boolean cadastrarPet(String idOuNome, CadastroPetDto dto){
          try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = abrigoRepository.getReferenceById(id);
            Pet pet = new Pet(false,abrigo);
            abrigo.getPets().add(pet);
            abrigoRepository.save(abrigo);
            return true;
          } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRepository.findByNome(idOuNome);
                Pet pet = new Pet(false,abrigo);
                abrigo.getPets().add(pet);
                abrigoRepository.save(abrigo);
                return true;
            } catch (EntityNotFoundException enfe) {
                return false;
            }
        }
    }



}
