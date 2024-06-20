package alura.foro.api.domain.topico.validaciones;

import alura.foro.api.domain.topico.DtoRegistroTopico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionTituloUnico implements ValidacionTopico {
    @Autowired
    TopicoRepository topicoRepository;

    @Override
    public void validacion(DtoRegistroTopico dto) {
        if(dto.titulo() == null) {
            throw new ValidacionIntegridad("El título no puede ser nulo");
        }

        boolean tituloExiste = topicoRepository.existsByTitulo(dto.titulo());
        if(tituloExiste) {
            throw new ValidacionIntegridad("Este título ya existe en el foro, prueba con otro");
        }
    }
}