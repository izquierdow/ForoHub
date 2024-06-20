package alura.foro.api.controllers;

import alura.foro.api.domain.respuesta.DtoActualizarRespuesta;
import alura.foro.api.domain.respuesta.DtoListadoRespuesta;
import alura.foro.api.domain.respuesta.DtoRegistroRespuesta;
import alura.foro.api.domain.respuesta.DtoRetornoRespuesta;
import alura.foro.api.domain.respuesta.servicios.ServicioRespuesta;
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

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private ServicioRespuesta service;

    @PostMapping
    @Transactional
    @Operation(summary = "Crear Respuesta",
            description = "Registra una nueva respuesta en la base de datos. " +
            "Requiere: { topico_id, respuesta, usuario_id }",
            tags = "Respuesta Controller")
    public ResponseEntity<DtoRetornoRespuesta> crearRespuesta(@RequestBody @Valid DtoRegistroRespuesta dto, UriComponentsBuilder builder) {
        var respuesta = service.crearRespuesta(dto, builder);
        return respuesta;
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "Listar Respuestas",
            description = "Muestra todas las respuestas activas de nuestra base de datos",
            tags = "Respuesta Controller")
    public ResponseEntity<Page<DtoListadoRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable page) {
        var respuestas = service.listarRespuestas(page);
        return respuestas;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar Respuesta",
            description = "Muestra la respuesta que coincida con el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Respuesta Controller")
    public ResponseEntity<DtoRetornoRespuesta> listarRespuesta(@PathVariable Long id) {
        var respuesta = service.listarRespuesta(id);
        return respuesta;
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar Respuesta",
            description = "Modifica la respuesta que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Respuesta Controller")
    public ResponseEntity<DtoRetornoRespuesta> actualizarRespuesta(@RequestBody @Valid DtoActualizarRespuesta dto, @PathVariable Long id) {
        var respuesta = service.actualizarRespuesta(dto, id);
        return respuesta;
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar Respuesta",
            description = "Desactiva la respuesta que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Respuesta Controller")
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id) {
        var respuesta = service.desactivarRespuesta(id);
        return respuesta;
    }

}

