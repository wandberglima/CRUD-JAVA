package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // metodo para fazer a consulta no banco de dados
    UserDetails findByLogin(String login);
}
