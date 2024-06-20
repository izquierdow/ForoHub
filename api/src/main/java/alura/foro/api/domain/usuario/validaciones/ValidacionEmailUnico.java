package alura.foro.api.domain.usuario.validaciones;

import alura.foro.api.domain.usuario.DtoActualizarUsuario;
import alura.foro.api.domain.usuario.DtoRegistroUsuario;
import alura.foro.api.domain.usuario.UsuarioRepository;
import alura.foro.api.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionEmailUnico implements ValidacionUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validacion(DtoRegistroUsuario dto) {
        boolean emailExiste = usuarioRepository.existsByEmail(dto.email());
        if(emailExiste) {
            throw new ValidacionIntegridad("El email ingresado ya existe.");
        }
    }

    public void validacionActualizar(DtoActualizarUsuario dto) {
        boolean emailExiste = usuarioRepository.existsByEmail(dto.email());
        if(emailExiste) {
            throw new ValidacionIntegridad("El email ingresado ya existe.");
        }
    }
}