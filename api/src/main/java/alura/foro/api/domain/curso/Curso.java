package alura.foro.api.domain.curso;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name="cursos")
@Entity(name = "Curso")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    private Boolean activo;

    public Curso(String nombre, CategoriaCurso categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Curso(DtoRegistroCurso dto) {
        this.activo = true;
        this.nombre = dto.nombre();
        this.categoria = dto.categoria();
    }

    public void desactivarCurso() {
        this.activo = false;
    }

    public void actualizarCurso(DtoActualizarCurso dto) {
        if(dto.nombre() != null) {
            this.nombre = dto.nombre();
        }
        if(dto.categoria() != null) {
            this.setCategoria(dto.categoria());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(getId(), curso.getId()) && Objects.equals(getNombre(), curso.getNombre()) && getCategoria() == curso.getCategoria() && Objects.equals(getActivo(), curso.getActivo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getCategoria(), getActivo());
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaCurso getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCurso categoria) {
        this.categoria = categoria;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
