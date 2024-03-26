package med.voll.api.domain.dtos.medicoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.dtos.enderecoDTO.DadosEnderecoDTO;
import med.voll.api.domain.dtos.medicoDTO.enums.Especialidade;

// Aqui Foi criado as validações do DTO
public record DadosCadastroMedico(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEnderecoDTO endereco) {
}