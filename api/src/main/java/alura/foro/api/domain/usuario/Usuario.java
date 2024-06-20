package alura.foro.api.domain.usuario;

import alura.foro.api.domain.topico.DtoActualizarTopico;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name="usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private boolean activo;

    public Usuario(@Valid DtoRegistroUsuario dto) {
        this.nombre = dto.nombre();
        this.email = dto.email();
        this.activo = true;
    }

    public void actualizarUsuario(@Valid DtoActualizarUsuario dto) {
        if(dto.nombre() != null) {
            this.nombre = dto.nombre();
        }
        if(dto.email() != null) {
            this.email = dto.email();
        }
    }

    public void desactivarUsuario() {
        this.activo = false;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
