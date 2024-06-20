package alura.foro.api.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroUsuario(
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String nombre,
        @NotNull(message = "El correo electrónico es obligatorio")
        @Email
        String email
) {
}