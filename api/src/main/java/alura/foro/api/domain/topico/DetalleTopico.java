package alura.foro.api.domain.topico;

import alura.foro.api.domain.curso.DtoRetornoCurso;
import alura.foro.api.domain.usuario.Usuario;

public record DetalleTopico (Long id, String titulo, String mensaje, EstatusTopico estatus, Long id_autor, DtoRetornoCurso curso) {
        public DetalleTopico (Topico topico) {
                this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getEstatus(), topico.getAutor().getId(),
                        new DtoRetornoCurso(topico.getCurso().getId(), topico.getCurso().getNombre(),
                                topico.getCurso().getCategoria()));
        }

}
