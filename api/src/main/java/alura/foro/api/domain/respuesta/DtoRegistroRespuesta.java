package alura.foro.api.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRegistroRespuesta (
        @NotNull(message = "El id del topico es obligatorio")
        Long topico_id,
        @NotBlank(message = "La respuesta es obligatoria")
        String respuesta,
        @NotNull(message = "El id del usuario es obligatorio")
        Long usuario_id ) {
}
