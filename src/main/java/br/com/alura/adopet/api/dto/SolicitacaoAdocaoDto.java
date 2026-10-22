package br.com.alura.adopet.api.dto;

public class SolicitacaoAdocaoDto {

    public record SolicitacaoAdocaoDto(Long idPet, Long idTutor, String motivo) {

    }

}
