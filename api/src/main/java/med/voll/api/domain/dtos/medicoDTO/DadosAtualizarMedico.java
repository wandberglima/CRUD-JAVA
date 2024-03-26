package med.voll.api.domain.dtos.medicoDTO;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.enderecoDTO.DadosEnderecoDTO;

public record DadosAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        DadosEnderecoDTO endereco) {

}
