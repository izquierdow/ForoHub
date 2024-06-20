package alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroCurso (
        @NotBlank(message = "El nombre del curso es obligatorio")
        String nombre,
        @NotNull(message = "La categoria es obligatoria")
        CategoriaCurso categoria
) {
}
