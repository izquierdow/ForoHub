package alura.foro.api.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    boolean existsByRespuesta(String respuesta);
    Page<Respuesta> findByActivoTrue(Pageable page);
}