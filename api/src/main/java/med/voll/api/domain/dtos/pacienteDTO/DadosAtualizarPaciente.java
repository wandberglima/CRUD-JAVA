package med.voll.api.domain.dtos.pacienteDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.enderecoDTO.DadosEnderecoDTO;

public record DadosAtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        @Valid
        DadosEnderecoDTO enderecoPaciente) {


}
