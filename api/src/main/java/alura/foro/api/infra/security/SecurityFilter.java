package alura.foro.api.infra.security;

import alura.foro.api.domain.moderador.ModeradorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ModeradorRepository moderadorRepository;

    /**
     * Filtra las solicitudes para autenticar a los usuarios a partir de los tokens de autorización.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtiene el encabezado de autorización de la solicitud.
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            // Extrae el token de autorización de la cadena del encabezado.
            var token = authHeader.replace("Bearer ", "");
            // Obtiene el nombre de usuario del token.
            var username = tokenService.getSubject(token);
            if (username != null) {
                // Busca el usuario en el repositorio basado en el nombre de usuario.
                var usuario = moderadorRepository.findByUsername(username);
                // Crea una autenticación basada en el usuario.
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                // Establece la autenticación en el contexto de seguridad.
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }

        filterChain.doFilter(request, response);
    }
}