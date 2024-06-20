package alura.foro.api.controllers;

import alura.foro.api.domain.curso.*;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Crear Curso",
            description = "Registra un nuevo curso en la base de datos. " +
            "Requiere: { nombre, categoria }",
            tags = "Curso Controller")
    public ResponseEntity<DtoRetornoCurso> crearCurso(@RequestBody @Valid DtoRegistroCurso dto, UriComponentsBuilder uriComponentsBuilder) {
        var curso = new Curso(dto);
        cursoRepository.save(curso);

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(url).body(new DtoRetornoCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }

    @GetMapping
    @Transactional
    @ResponseBody
    @Operation(summary = "Listar Cursos",
            description = "Muestra todos los cursos activos de nuestra base de datos",
            tags = "Curso Controller")
    public ResponseEntity<Page<DtoListadoCurso>> listarCursos(@PageableDefault(size = 5) Pageable paginacion){

        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DtoListadoCurso::new));
    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(summary = "Listar Curso",
            description = "Muestra el curso que coincida con el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Curso Controller")
    public ResponseEntity<DtoRetornoCurso> listarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoRetornoCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar Curso",
            description = "Modifica el curso que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
                      tags = "Curso Controller")
    public ResponseEntity<DtoRetornoCurso> actualizarCurso(@RequestBody @Valid DtoActualizarCurso dto) {
        Curso curso = cursoRepository.getReferenceById(dto.id());
        curso.actualizarCurso(dto);
        return ResponseEntity.ok(new DtoRetornoCurso(curso.getId(),curso.getNombre(), curso.getCategoria()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar Curso",
            description = "Desactiva el curso que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
                      tags = "Curso Controller")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id) {
        Curso curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }

}
