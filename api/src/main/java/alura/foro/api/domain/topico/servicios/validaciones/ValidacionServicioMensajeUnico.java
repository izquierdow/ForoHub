package alura.foro.api.domain.topico.servicios.validaciones;

import alura.foro.api.domain.topico.DtoActualizarTopico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionServicioMensajeUnico implements ValidacionPut {
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validacion(DtoActualizarTopico dto) {
        boolean mensajeExiste = topicoRepository.existsByMensaje(dto.mensaje());
        if(mensajeExiste) {
            throw new ValidacionIntegridad("Este mensaje ya existe en nuestro foro");
        }
    }

}