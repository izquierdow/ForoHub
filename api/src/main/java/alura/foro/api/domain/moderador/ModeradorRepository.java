package alura.foro.api.domain.moderador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ModeradorRepository extends JpaRepository<Moderador, Long> {

    UserDetails findByUsername(String username);

}
