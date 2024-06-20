package alura.foro.api.domain.respuesta.servicios;

import alura.foro.api.domain.respuesta.*;
import alura.foro.api.domain.respuesta.servicios.validaciones.ValidacionRespuesta;
import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.topico.TopicoRepository;
import alura.foro.api.domain.usuario.Usuario;
import alura.foro.api.domain.usuario.UsuarioRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;


import java.net.URI;
import java.util.List;

@Service
public class ServicioRespuesta {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidacionRespuesta> validaciones;

    public ResponseEntity<DtoRetornoRespuesta> crearRespuesta(@Valid DtoRegistroRespuesta dto, UriComponentsBuilder builder) {

        validaciones.forEach(v -> v.validacion(dto));

        Topico topico = topicoRepository.getReferenceById(dto.topico_id());
        Usuario usuario = usuarioRepository.getReferenceById(dto.usuario_id());
        Respuesta respuesta = new Respuesta(topico, dto.respuesta(), usuario);
        respuestaRepository.save(respuesta);

        URI url = builder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(url).body(new DtoRetornoRespuesta(topico, respuesta, usuario)) ;
    }

    public ResponseEntity<Page<DtoListadoRespuesta>> listarRespuestas(Pageable page) {
        Page<DtoListadoRespuesta> answers = respuestaRepository.findByActivoTrue(page).map(DtoListadoRespuesta::new);
        return ResponseEntity.ok(answers);
    }

    public ResponseEntity<DtoRetornoRespuesta> listarRespuesta(Long id) {
        if(!respuestaRepository.getReferenceById(id).isActivo()) {
            throw new ValidacionIntegridad("¡Este elemento no existe!");
        }

        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        DtoRetornoRespuesta answerData = new DtoRetornoRespuesta(respuesta.getTopico(), respuesta, respuesta.getUsuario());
        return ResponseEntity.ok(answerData);
    }

    public ResponseEntity<DtoRetornoRespuesta> actualizarRespuesta(@Valid DtoActualizarRespuesta dto, Long id) {
        if(!respuestaRepository.existsById(id)) {
            throw new ValidacionIntegridad("La respuesta relacionada con este ID no existe.");
        }
        if(respuestaRepository.existsByRespuesta(dto.respuesta())) {
            throw new ValidacionIntegridad("Respuesta duplicada.");
        }

        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.actualizarRespuesta(dto);
        return ResponseEntity.ok(new DtoRetornoRespuesta(respuesta.getTopico(), respuesta, respuesta.getUsuario()));
    }

    public ResponseEntity<?> desactivarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.desactivarRespuesta();
        return ResponseEntity.noContent().build();
    }
}
