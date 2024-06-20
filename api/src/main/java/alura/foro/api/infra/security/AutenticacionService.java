package alura.foro.api.infra.security;

import alura.foro.api.domain.moderador.ModeradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private ModeradorRepository moderadorRepository;

    /**
     * Carga y devuelve detalles de usuario basados en su nombre de usuario.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca un moderador en el repositorio a través del nombre de usuario.
        // Si el moderador no se encuentra, se lanza una excepción UsernameNotFoundException.
        return moderadorRepository.findByUsername(username);
    }

}