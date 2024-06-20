package alura.foro.api.infra.errores;

/**
 * Excepción personalizada para manejar errores de validación de integridad o de negocio.
 * Puede lanzarse cuando se encuentran problemas de integridad de datos o validaciones que no se cumplen.
 */
public class ValidacionIntegridad extends RuntimeException {

    /**
     * Constructor que permite establecer un mensaje de error personalizado.
     * @param mensaje El mensaje de error que describe la causa de la excepción.
     */
    public ValidacionIntegridad(String mensaje) {
        super(mensaje);
    }
}
