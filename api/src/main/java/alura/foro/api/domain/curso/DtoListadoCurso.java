package alura.foro.api.domain.curso;

public record DtoListadoCurso(Long id, String nombre, CategoriaCurso categoria) {
    public DtoListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}