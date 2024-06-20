package alura.foro.api.domain.topico;

import alura.foro.api.domain.curso.DtoRegistroCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroTopico(
        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull(message = "El ID del usuario autor es obligatorio")
        @Valid
        Long id_autor,

        @NotNull(message = "Los datos del curso son obligatorios")
        @Valid
        DtoRegistroCurso curso
) {
}
