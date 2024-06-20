package alura.foro.api.domain.usuario;

import jakarta.validation.constraints.Email;

public record DtoActualizarUsuario(
        String nombre,
        @Email
        String email
) {
}