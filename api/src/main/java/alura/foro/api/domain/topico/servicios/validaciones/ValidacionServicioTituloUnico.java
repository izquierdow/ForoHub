package alura.foro.api.domain.topico.servicios.validaciones;

import alura.foro.api.domain.topico.DtoActualizarTopico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionServicioTituloUnico implements ValidacionPut {
    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validacion(DtoActualizarTopico dto) {
        boolean title = topicoRepository.existsByTitulo(dto.titulo());
        if(title) {
            throw new ValidacionIntegridad("Este título ya existe en el foro, prueba con otro");
        }
    }
}