package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.entities.Medico;
import med.voll.api.medico.DadosAtualizarMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
//sdsdsds
    @Autowired
    private MedicoRepository repository;

    // requisição do corpo pelo metodo @requestBody e o @Transaction para setar uma
    // transação ativa com o banco de dados
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
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
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable pageable) {
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
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.excluir();

        // repository.deleteById(id); delete simples
    }

}
