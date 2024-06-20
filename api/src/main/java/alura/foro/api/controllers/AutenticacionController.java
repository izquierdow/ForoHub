package alura.foro.api.controllers;

import alura.foro.api.domain.moderador.DtoAutenticacionModerador;
import alura.foro.api.domain.moderador.Moderador;
import alura.foro.api.infra.security.DtoJWTToken;
import alura.foro.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService service;

    @PostMapping
    @Operation(summary = "Genera JWT",
            description = "Genera un token JWT para nuestra correcta autenticación en el sistema." +
            "Requiere: { username, password }",
            tags = "Autenticacion Controller")
    public ResponseEntity<?> autenticarModerador(@RequestBody @Valid DtoAutenticacionModerador dto) {
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());

        var authenticateUser = authenticationManager.authenticate(AuthToken);
        var JWTtoken = service.generateToken((Moderador) authenticateUser.getPrincipal());
        return ResponseEntity.ok(new DtoJWTToken(JWTtoken));
    }

}

