package alura.foro.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findByActivoTrue(Pageable paginacion);

    @Query("""
			SELECT COUNT(c) > 0
			FROM Curso c
			WHERE c.activo = true
			AND c.nombre = :nombre
			""")
    boolean findByNombre(@Param("nombre") String nombre);

    @Query("""
			SELECT c
			FROM Curso c
			WHERE c.activo = true
			AND c.nombre = :nombre
			""")
    Curso getByNombre(@Param("nombre") String nombre);

}