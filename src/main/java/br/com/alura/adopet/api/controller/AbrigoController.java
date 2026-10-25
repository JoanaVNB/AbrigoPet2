package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

//    @PostMapping
//    @Transactional
//    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroAbrigoDto dto) {
//        boolean nomeJaCadastrado = repository.existsByNome(dto.Nome());
//        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.Telefone());
//        boolean emailJaCadastrado = repository.existsByEmail(dto.email());
//
//        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
//            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!");
//        } else {
//            //repository.save(adocao); deve ser um metodo Service
//            this.abrigoService.cadastrar(dto);
//            return ResponseEntity.ok().build();
//        }
//    }

//    @GetMapping("/{idOuNome}/pets")
//    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
//        List<Pet> pets = abrigoService.listarPets(idOuNome);
//        if (pets != null){
//            return ResponseEntity.ok(pets);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/{idOuNome}/pets")
//    @Transactional
//    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPetDto dto) {
//        boolean b = abrigoService.cadastrarPet(idOuNome, dto);
//        if (b == true) {
//                return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listarAbrigo() {
        List<AbrigoDto> abrigos = abrigoService.listar();
        return ResponseEntity.ok(abrigos);
    }

}
