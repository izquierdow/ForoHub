package alura.foro.api.domain.topico;

import alura.foro.api.domain.curso.DtoRetornoCurso;
import alura.foro.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoListadoTopico (Long id, String titulo, String mensaje, LocalDateTime fecha_creacion,
                                EstatusTopico estatus, Long id_autor, DtoRetornoCurso curso) {

    public DtoListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
            topico.getEstatus(), topico.getAutor().getId(),
                new DtoRetornoCurso(topico.getCurso().getId(),
                    topico.getCurso().getNombre(), topico.getCurso().getCategoria()));
    }

}
