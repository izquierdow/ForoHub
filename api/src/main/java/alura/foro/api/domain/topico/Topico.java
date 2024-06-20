package alura.foro.api.domain.topico;

import alura.foro.api.domain.curso.Curso;
import alura.foro.api.domain.respuesta.Respuesta;
import alura.foro.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private EstatusTopico estatus = EstatusTopico.ABIERTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private boolean activo;

    public Topico (String titulo, String mensaje, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
    }

    public Topico(String titulo, String mensaje, LocalDateTime fecha_creacion, EstatusTopico estatus, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha_creacion = fecha_creacion;
        this.estatus = estatus;
        this.autor = autor;
        this.curso = curso;
        activo = true;
    }

    public void actualizarTopico(DtoActualizarTopico dto) {
        if(dto.titulo() != null) {
            this.titulo = dto.titulo();
        }
        if(dto.mensaje() != null) {
            this.mensaje = dto.mensaje();
        }
        if(dto.estatus() != null) {
            this.estatus = dto.estatus();
        }
    }

    public void desactivarTopico() {
        this.activo = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fecha_creacion;
    }

    public void setFechaCreacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public EstatusTopico getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusTopico estatus) {
        this.estatus = estatus;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}