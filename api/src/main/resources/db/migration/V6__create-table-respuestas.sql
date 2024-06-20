CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topico_id BIGINT NOT NULL,
    respuesta TEXT NOT NULL,
    usuario_id BIGINT NOT NULL,
    respuesta_fecha DATETIME NOT NULL,
    FOREIGN KEY (topico_id) REFERENCES topicos (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);