package br.ufpb.dcx.dsc.figurinhas.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O username não pode ser vazio")
        String username,

        @NotBlank(message = "A senha não pode ser vazia")
        String password
) {
}