package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.dtos.medicoDTO.DadosAtualizarMedico;
import med.voll.api.domain.dtos.medicoDTO.DadosCadastroMedico;
import med.voll.api.domain.dtos.medicoDTO.DadosDetalhamentoMedico;
import med.voll.api.domain.dtos.medicoDTO.DadosListagemMedicos;
import med.voll.api.domain.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    // requisição do corpo pelo metodo @requestBody e o @Transaction para setar uma
    // transação ativa com o banco de dados
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    /*
     * Metodo somente de leitura dos dados do banco para listar todos os dados
     * cadastrados do médico
     * para utilizar paginação dentro do metodo GET definimos o parametro PAGEABLE
     * do spring data domain
     * em seguida criamos na sobrecarga do metodo findall como PAGEABLE
     * e o nosso LIST tem de ser alterado para PAGE pois assim ele retornara os
     * dados da nossa paginação.
     */
    @GetMapping("/listar")
    public ResponseEntity<Page<DadosListagemMedicos>> listar(
            @PageableDefault(
                    size = 10,
                    sort = {"nome"}
            ) Pageable pageable) {
        /*
         * a principio o return esta devolvendo os dados do repository de medicos
         * para fazer a conversão usamos o .stream e o .map do JAVA 8 para fazer um
         * mapeamento dos
         * Dados da Listagem de medicos
         * return
         * repository.findAll(pageable).stream().map(DadosListagemMedicos::new).toList()
         * ;
         *
         * ao tratar a paginação não aplica os metodos .stream() e o .toList() para
         * conversão em lista pois a
         * paginação já devolve um mapeamento do DTO.
         */
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        // o recomendado é devolver um DTO e não a entidade neste ponto;
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    // padronizar o status de retorno spring para a exclusão com o status 204
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
        // repository.deleteById(id); delete simples
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {

        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


}
