package alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DtoActualizarCurso (
        @NotNull Long id,
        String nombre,
        CategoriaCurso categoria
) {
}