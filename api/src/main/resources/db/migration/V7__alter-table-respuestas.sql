ALTER TABLE respuestas ADD activo TINYINT;
UPDATE respuestas set activo = 1;