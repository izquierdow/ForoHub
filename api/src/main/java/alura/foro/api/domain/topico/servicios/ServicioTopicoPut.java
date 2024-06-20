package alura.foro.api.domain.topico.servicios;

import alura.foro.api.domain.topico.DtoActualizarTopico;
import alura.foro.api.domain.topico.DtoDetalleTopico;
import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.domain.topico.servicios.validaciones.ValidacionPut;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioTopicoPut {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    List<ValidacionPut> validaciones;

    public DtoDetalleTopico actualizarTopico(Long id, DtoActualizarTopico dto) {
        if(!topicoRepository.existsById(id) || topicoRepository.findByActivo(false)) {
            throw new ValidacionIntegridad("El Tema que quieres actualizar no existe");
        }

        validaciones.forEach(p -> p.validacion(dto));

        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(dto);
        return new DtoDetalleTopico(topico);
    }
}