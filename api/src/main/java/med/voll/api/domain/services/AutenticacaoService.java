package med.voll.api.domain.services;

import med.voll.api.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    //    para injetar essa dependencia pode ser usando o @Autowired ou gerando um contrutor do lombok com o atributo

    //    Exemplo de Construtor
    //    public AutenticacaoService(final UsuarioRepository usuarioRepository) {
    //        this.usuarioRepository = usuarioRepository;
    //    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Sempre que o usuário fizer login no projeto spring esse metodo sera chamado
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Nesse ponto temos que acessar o banco de dados pelo repository
        return usuarioRepository.findByLogin(username);

    }
}
