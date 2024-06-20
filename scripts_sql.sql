-- 1. Crea la database a usar en el proyecto de SpringBoot
CREATE SCHEMA IF NOT EXISTS alura_foro;

-- 2. Registrar el moderador con usuario "daniel.herrera" y clave encriptada por BCrypt "123456"
INSERT INTO moderadores (username, password) VALUES ('daniel.herrera','$2a$12$1zCK4ldKFFa08gY5Ydiy4.5GtrRDb6q0uggdseQ92nKxISTxQoYKm');

-- Si ocurre un error de la migracion
# DELETE FROM flyway_schema_history WHERE success = 0;

-- Eliminar definitivamente el historial de migraciones de flyway
# DROP TABLE flyway_schema_history;

-- Rehacer historial de migraciones de flyway
# CREATE TABLE flyway_schema_history (
#     installed_rank INT NOT NULL,
#     version VARCHAR(50),
#     description VARCHAR(200),
#     type VARCHAR(20) NOT NULL,
#     script VARCHAR(1000) NOT NULL,
#     checksum INT,
#     installed_by VARCHAR(100) NOT NULL,
#     installed_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
#     execution_time INT NOT NULL,
#     success BOOLEAN NOT NULL
# );

-- Mostrar los Topicos
# SELECT * FROM topicos;

-- Desactivar un Topico por ID
# UPDATE topicos SET activo = 1 WHERE id = X;