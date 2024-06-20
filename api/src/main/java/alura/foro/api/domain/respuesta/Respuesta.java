package alura.foro.api.domain.respuesta;

import alura.foro.api.domain.topico.Topico;
import alura.foro.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(name = "respuesta")
    private String respuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "respuesta_fecha")
    private LocalDateTime respuesta_fecha;

    @Column(name = "activo")
    private boolean activo;

    public Respuesta(Topico topico, String respuesta, Usuario usuario) {
        this.topico = topico;
        this.respuesta = respuesta;
        this.usuario = usuario;
        this.respuesta_fecha = LocalDateTime.now();
        this.activo = true;
    }

    public void actualizarRespuesta(@Valid DtoActualizarRespuesta dto) {
        if(dto.respuesta() != null) {
            this.respuesta = dto.respuesta();
        }
    }

    public void desactivarRespuesta() {
        this.activo = false;
    }

    public Long getId() {
        return id;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getRespuestaFecha() {
        return respuesta_fecha;
    }

    public void setRespuestaFecha(LocalDateTime respuesta_fecha) {
        this.respuesta_fecha = respuesta_fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
