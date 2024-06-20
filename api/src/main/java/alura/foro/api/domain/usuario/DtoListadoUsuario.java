package alura.foro.api.domain.usuario;

public record DtoListadoUsuario(Long id, String nombre, String email) {

    public DtoListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }

}
