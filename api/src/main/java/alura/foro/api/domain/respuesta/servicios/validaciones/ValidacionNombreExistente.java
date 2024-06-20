package alura.foro.api.domain.respuesta.servicios.validaciones;

import alura.foro.api.domain.respuesta.DtoRegistroRespuesta;
import alura.foro.api.domain.usuario.Usuario;
import alura.foro.api.domain.usuario.UsuarioRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacionNombreExistente implements ValidacionRespuesta {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validacion (DtoRegistroRespuesta dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.usuario_id());

        if(!usuarioRepository.existsByNombre(usuario.getNombre())) {
            throw new ValidacionIntegridad("El Usuario ingresado no existe.");
        }
    }
}