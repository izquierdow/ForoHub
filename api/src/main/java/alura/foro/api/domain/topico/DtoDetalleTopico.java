package alura.foro.api.domain.topico;

import alura.foro.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoDetalleTopico (Long id, String titulo, String mensaje, LocalDateTime fecha_creacion, Long id_autor, EstatusTopico estatus) {

    public DtoDetalleTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getAutor().getId(), topico.getEstatus());
    }

}
