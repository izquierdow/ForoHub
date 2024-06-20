package alura.foro.api.domain.topico.servicios;

import alura.foro.api.domain.curso.CursoRepository;
import alura.foro.api.domain.topico.*;
import alura.foro.api.domain.topico.validaciones.ValidacionTopico;
import alura.foro.api.domain.usuario.UsuarioRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioTopicoPost {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidacionTopico> validaciones;

    public ResponseEntity<DtoDetalleTopico> crearTopico(DtoRegistroTopico dto, UriComponentsBuilder builder) {
        if(!cursoRepository.findByNombre(dto.curso().nombre())) {
            throw new ValidacionIntegridad("El Curso seleccionado no fue encontrado");
        }
        if(!usuarioRepository.existsById(dto.id_autor())){
            throw new ValidacionIntegridad("El id del usuario autor no fue encontrado");
        }

        validaciones.forEach(v -> v.validacion(dto));

        var curso = cursoRepository.getByNombre(dto.curso().nombre());
        var autor = usuarioRepository.getReferenceById(dto.id_autor());
        var topico = new Topico(dto.titulo(), dto.mensaje(), LocalDateTime.now(), EstatusTopico.ABIERTO,
                autor, curso);

        topicoRepository.save(topico);

        URI url = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new DtoDetalleTopico(topico));
    }
}