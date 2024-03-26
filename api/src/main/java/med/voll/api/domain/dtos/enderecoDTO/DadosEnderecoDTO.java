package med.voll.api.domain.dtos.enderecoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


// Aqui Foi criado as validações do DTO
public record DadosEnderecoDTO(

        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,

        String complemento,

        String numero) {

}
