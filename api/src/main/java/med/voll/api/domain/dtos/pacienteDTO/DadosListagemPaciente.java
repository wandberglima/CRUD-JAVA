package med.voll.api.domain.dtos.pacienteDTO;

import med.voll.api.domain.entities.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String telefone, String cpf) {


    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());
    }

}
