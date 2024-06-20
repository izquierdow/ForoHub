package alura.foro.api.domain.topico;

public record DtoActualizarTopico(
        String titulo,
        String mensaje,
        EstatusTopico estatus
) {
}