package alura.foro.api.domain.usuario.validaciones;

import alura.foro.api.domain.usuario.DtoActualizarUsuario;
import alura.foro.api.domain.usuario.DtoRegistroUsuario;
import alura.foro.api.domain.usuario.UsuarioRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionNombreUnico implements ValidacionUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validacion(DtoRegistroUsuario dto) {
        boolean usuarioExiste = usuarioRepository.existsByNombre(dto.nombre());
        if(usuarioExiste) {
            throw new ValidacionIntegridad("El nombre de usuario elegido ya existe, intenta uno nuevo.");
        }
    }

    @Override
    public void validacionActualizar(DtoActualizarUsuario dto) {
        boolean usuarioExiste = usuarioRepository.existsByNombre(dto.nombre());
        if(usuarioExiste) {
            throw new ValidacionIntegridad("El usuario elegido ya existe, intenta uno nuevo.");
        }
    }
}