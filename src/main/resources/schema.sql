-- Crear tabla Serie
CREATE TABLE Serie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    plataforma VARCHAR(100) NOT NULL,
    caratula VARCHAR(255),
    sinopsis VARCHAR(255)
);

-- Crear tabla Valoracion
CREATE TABLE Valoracion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serie_id BIGINT NOT NULL,
    autor VARCHAR(100) NOT NULL,
    valoracion DECIMAL(3, 1) NOT NULL CHECK (valoracion BETWEEN 1 AND 5),
    
    FOREIGN KEY (serie_id) REFERENCES Serie(id) ON DELETE CASCADE
);