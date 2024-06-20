package alura.foro.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * Configuración de la seguridad de la aplicación.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Deshabilitar la protección CSRF (Cross-Site Request Forgery).
                .csrf(csrf -> csrf.disable())
                // Configura la política de gestión de sesiones como "sin estado" (STATELESS).
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Define las reglas de autorización de las solicitudes HTTP.
                .authorizeHttpRequests(auth -> auth
                        // Permitir todas las solicitudes POST a la ruta "/login" sin autenticación.
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        // Permitir el acceso a rutas de Swagger y documentación API sin autenticación.
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        // Requerir autenticación para todas las demás solicitudes.
                        .anyRequest().authenticated())
                // Añadir un filtro de seguridad personalizado antes del filtro de autenticación por nombre de usuario y contraseña.
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configuración del administrador de autenticación.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configuración del codificador de contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // Utilizar el algoritmo de codificación de contraseñas BCrypt.
        return new BCryptPasswordEncoder();
    }
}
