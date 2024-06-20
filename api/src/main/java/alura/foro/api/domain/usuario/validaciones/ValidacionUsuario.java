package alura.foro.api.domain.usuario.validaciones;

import alura.foro.api.domain.usuario.DtoActualizarUsuario;
import alura.foro.api.domain.usuario.DtoRegistroUsuario;

public interface ValidacionUsuario {

    public void validacion(DtoRegistroUsuario dto);

    public void validacionActualizar(DtoActualizarUsuario dto);
}
