package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroAbrigoDto(@NotNull Long id, @NotBlank String Nome, @NotBlank String Telefone, @NotBlank String email) {
}
