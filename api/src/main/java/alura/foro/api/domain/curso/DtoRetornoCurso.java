package alura.foro.api.domain.curso;

public record DtoRetornoCurso(Long id, String nombre, CategoriaCurso categoria) {
    public DtoRetornoCurso(Long id, String nombre, CategoriaCurso categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }
}