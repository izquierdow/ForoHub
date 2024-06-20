package alura.foro.api.domain.respuesta.servicios.validaciones;

import alura.foro.api.domain.respuesta.DtoActualizarRespuesta;
import alura.foro.api.domain.respuesta.DtoRegistroRespuesta;
import alura.foro.api.domain.respuesta.RespuestaRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionRespuestaUnica implements ValidacionRespuesta {
    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    public void validacion(DtoRegistroRespuesta dto) {
        if(respuestaRepository.existsByRespuesta(dto.respuesta())) {
            throw new ValidacionIntegridad("Esta respuesta ya existe.");
        }
    }

    public void validacionActualizar(DtoActualizarRespuesta dto) {
        if(respuestaRepository.existsByRespuesta(dto.respuesta())) {
            throw new ValidacionIntegridad("Esta respuesta ya existe.");
        }
    }
}