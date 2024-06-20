package alura.foro.api.domain.respuesta;

import alura.foro.api.domain.topico.DtoDetalleTopico;
import alura.foro.api.domain.usuario.DtoRetornoUsuario;

import java.time.LocalDateTime;

public record DtoListadoRespuesta (DtoDetalleTopico topico, Long id_respuesta, String respuesta, LocalDateTime respuesta_fecha, DtoRetornoUsuario usuario) {
    public DtoListadoRespuesta (Respuesta respuesta) {
        this(new DtoDetalleTopico(respuesta.getTopico()),respuesta.getId(), respuesta.getRespuesta(), respuesta.getRespuestaFecha(),
                new DtoRetornoUsuario(respuesta.getUsuario().getId(), respuesta.getUsuario().getNombre(), respuesta.getUsuario().getEmail()));
    }
}