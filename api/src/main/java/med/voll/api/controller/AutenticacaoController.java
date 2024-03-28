package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.usuarioDTO.DadosAutenticacao;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // para que o spring injete automaticamente o objeto, essa
    // configuração fica no securityConfiguration
    @Autowired
    private AuthenticationManager manager;

    // @RequestBody para vir o corpo da requisição e @Valid para validar com o BeanValidation os dados
    // dos DTO DadosAutenticacao
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        //  criando a clase esperada pelo spring e converter o dto do
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }



}
