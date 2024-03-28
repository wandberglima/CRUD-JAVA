package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Anotações para sinalizar ao spring que sera alterado as configurações de segurança
// para não ser mais stateful para Stateless
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    // a anotação @Bean serve para ler automaticamente o metodo e devolver o objeto
    // para o spring ou um objeto que possa injetar em algum controller
    // exportando uma classe e fazendo com que ele consiga carregar e
    // realizar injetar as dependencias em outras classes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                // aqui é desabilitado o cross-site request forgery (csrf)
                // pois o token já protege contra isso e mudando a autenticação
                // da api de stateful para stateless
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
