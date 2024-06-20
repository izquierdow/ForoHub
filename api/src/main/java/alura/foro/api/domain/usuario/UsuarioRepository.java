package alura.foro.api.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
    Page<Usuario> findByActivoTrue(Pageable page);
    Usuario getByNombre(String nombre);

}