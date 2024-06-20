package alura.foro.api.controllers;

import alura.foro.api.domain.usuario.*;
import alura.foro.api.domain.usuario.validaciones.ValidacionUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidacionUsuario> validaciones;

    @PostMapping
    @Transactional
    @Operation(summary = "Crear Usuario",
            description = "Registra un nuevo usuario en la base de datos. " +
                          "Requiere: { nombre, email }",
            tags = "Usuario Controller")
    public ResponseEntity<DtoRetornoUsuario> crearUsuario(@RequestBody @Valid DtoRegistroUsuario dto, UriComponentsBuilder builder) {

        validaciones.forEach(v -> v.validacion(dto));

        Usuario usuario = new Usuario(dto);
        usuarioRepository.save(usuario);

        URI url = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(new DtoRetornoUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }

    @GetMapping
    @ResponseBody
    @Operation(summary = "Listar Usuarios",
            description = "Muestra todos los usuarios activos de nuestra base de datos",
            tags = "Usuario Controller")
    public ResponseEntity<Page<DtoListadoUsuario>> listarUsuarios(@PageableDefault(size = 5) Pageable page) {
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(page).map(DtoListadoUsuario::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar Usuario",
            description = "Muestra el usuario que coincida con el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Usuario Controller")
    public ResponseEntity<DtoRetornoUsuario> listarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoRetornoUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar Usuario",
            description = "Modifica el usuario que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
          tags = "Usuario Controller")
    public ResponseEntity<DtoRetornoUsuario> actualizarUsuario(@RequestBody @Valid DtoActualizarUsuario dto, @PathVariable Long id) {

        validaciones.forEach(v -> v.validacionActualizar(dto));

        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.actualizarUsuario(dto);

        return ResponseEntity.ok(new DtoRetornoUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar Usuario",
            description = "Desactiva el usuario que coincida en el ID indicado en la base de datos. " +
                      "El ID se proporciona como parámetro en la URL de la solicitud.",
            tags = "Usuario Controller")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

}