package alura.foro.api.domain.moderador;

import jakarta.validation.constraints.NotBlank;

public record DtoAutenticacionModerador (
        @NotBlank(message = "El usuario es obligatorio")
        String username,
        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}