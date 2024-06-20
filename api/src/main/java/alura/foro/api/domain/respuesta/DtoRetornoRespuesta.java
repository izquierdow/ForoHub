package alura.foro.api.domain.respuesta;

import alura.foro.api.domain.topico.DtoDetalleTopico;
import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.usuario.DtoRetornoUsuario;
import alura.foro.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DtoRetornoRespuesta(DtoDetalleTopico topico, Long respuesta_id, String respuesta, LocalDateTime respuesta_fecha, DtoRetornoUsuario usuario) {
    public DtoRetornoRespuesta(Topico topico, Respuesta respuesta, Usuario usuario) {
        this (new DtoDetalleTopico(topico), respuesta.getId(), respuesta.getRespuesta(), respuesta.getRespuestaFecha(),
                new DtoRetornoUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }
}