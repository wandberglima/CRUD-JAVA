package med.voll.api.domain.dtos.medicoDTO;

import med.voll.api.domain.dtos.medicoDTO.enums.Especialidade;
import med.voll.api.domain.entities.Medico;

public record DadosListagemMedicos(Boolean ativo, Long id, String nome, String email, String crm,
                                   Especialidade especialidade) {

    //contrutor para ser utilizado na conversão do metodo de listagem.
    public DadosListagemMedicos(Medico medico) {
        this(medico.getAtivo(), medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }


}
