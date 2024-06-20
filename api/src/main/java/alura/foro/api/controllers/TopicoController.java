package alura.foro.api.controllers;

import alura.foro.api.domain.topico.*;
import alura.foro.api.domain.topico.servicios.ServicioTopicoDelete;
import alura.foro.api.domain.topico.servicios.ServicioTopicoGet;
import alura.foro.api.domain.topico.servicios.ServicioTopicoPost;
import alura.foro.api.domain.topico.servicios.ServicioTopicoPut;
import alura.foro.api.domain.usuario.DtoRetornoUsuario;
import alura.foro.api.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ServicioTopicoPost postService;
    @Autowired
    private ServicioTopicoGet getService;
    @Autowired
    private ServicioTopicoPut putService;
    @Autowired
    private ServicioTopicoDelete deleteService;


    @PostMapping
    @Transactional
    @Operation(summary = "Crear topico",
            description = "Registra un nuevo topico en la base de datos. " +
            "Requiere: { título, mensaje, id_autor, curso }",
            tags = "Topico Controller")
    public ResponseEntity<DtoDetalleTopico> crearTopico(@RequestBody @Valid DtoRegistroTopico dto, UriComponentsBuilder uriComponentsBuilder) {
        var topicoRespuesta = postService.crearTopico(dto, uriComponentsBuilder);
        return topicoRespuesta;
    }



    @GetMapping
    @ResponseBody
    @Operation(summary = "Listar topicos",
            description = "Muestra todos los topicos activos de nuestra base de datos",
            tags = "Topico Controller")
    public ResponseEntity<Page<DtoListadoTopico>> listarTopicos(@PageableDefault(size = 5) Pageable pages) {
        Page<DtoListadoTopico> listadoTopicos = getService.listarTopicos(pages);
        return ResponseEntity.ok(listadoTopicos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar topico",
            description = "Muestra el topico que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Topico Controller")
    public ResponseEntity<DetalleTopico> listarTopico(@PathVariable Long id) {
        var topico = getService.listarTopico(id);
        var detalleTopico = new DetalleTopico(topico);
        return ResponseEntity.ok(detalleTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar topico",
            description = "Modifica el topico que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Topico Controller")
    public ResponseEntity<DtoDetalleTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DtoActualizarTopico dto) {
        var detalleTopico = putService.actualizarTopico(id, dto) ;
        return ResponseEntity.ok(detalleTopico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar topico",
            description = "Desactiva el topico que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Topico Controller")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        deleteService.desactivarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
