package alura.foro.api.domain.respuesta.servicios.validaciones;

import alura.foro.api.domain.respuesta.DtoRegistroRespuesta;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionTopicoExistente implements ValidacionRespuesta {
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validacion(DtoRegistroRespuesta dto) {
        if(!topicoRepository.existsById(dto.topico_id())) {
            throw new ValidacionIntegridad("El Tema seleccionado no existe.");
        }
    }
}