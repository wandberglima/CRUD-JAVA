package med.voll.api.domain.dtos.medicoDTO;

import med.voll.api.domain.entities.Endereco;
import med.voll.api.domain.dtos.medicoDTO.enums.Especialidade;
import med.voll.api.domain.entities.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
