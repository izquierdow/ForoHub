package alura.foro.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
			SELECT COUNT (t) > 0
			FROM Topico t
			WHERE t.activo = :activo
			""")
    boolean	findByActivo(@Param("activo") boolean activo) ;

    @Query("""
			SELECT COUNT (t) > 0
			FROM Topico t
			WHERE t.mensaje = :mensaje
			
			""")
    boolean existsByMensaje(@Param("mensaje") String mensaje);

    boolean existsByTitulo(String titulo);

    Page<Topico> findByActivoTrue(Pageable pages);

}
