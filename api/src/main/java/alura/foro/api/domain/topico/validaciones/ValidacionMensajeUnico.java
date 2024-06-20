package alura.foro.api.domain.topico.validaciones;

import alura.foro.api.domain.topico.DtoRegistroTopico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMensajeUnico implements ValidacionTopico {
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validacion(DtoRegistroTopico dto) {
        if(dto.mensaje() == null) {
            throw new ValidacionIntegridad("El mensaje no puede ser nulo");
        }

        boolean mensajeExiste = topicoRepository.existsByMensaje(dto.mensaje());
        if(mensajeExiste) {
            throw new ValidacionIntegridad("Este mensaje ya existe en nuestro foro");
        }
    }
}
