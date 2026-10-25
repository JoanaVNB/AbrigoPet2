package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

    Abrigo findByNome(String nome);

    boolean existsByNomeOrTelefoneOrEmail(@NotBlank String nome, @NotBlank String telefone, @NotBlank String email);
}
