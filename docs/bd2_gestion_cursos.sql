-- =============================================================
-- Sistema de Gestión de Cursos - Microservicios
-- Evaluación N° 03 - Desarrollo de Aplicaciones Web (Tecsup)
-- Estudiante: Faustino Quispe Alexander Oliver
--
-- Cada microservicio tiene su propia base de datos.
-- Perfil dev  : bases *_dev; Hibernate crea las tablas (ddl-auto: update).
-- Perfil prod : bases sin sufijo; las tablas deben existir (ddl-auto: validate).
-- =============================================================

-- -------------------------------------------------------------
-- 1. Bases de datos
-- -------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS bd2_docente;
CREATE DATABASE IF NOT EXISTS bd2_curso;
CREATE DATABASE IF NOT EXISTS bd2_inscripcion;

CREATE DATABASE IF NOT EXISTS bd2_docente_dev;
CREATE DATABASE IF NOT EXISTS bd2_curso_dev;
CREATE DATABASE IF NOT EXISTS bd2_inscripcion_dev;

-- -------------------------------------------------------------
-- 2. docente-service (puerto 8081)
-- -------------------------------------------------------------
USE bd2_docente;

CREATE TABLE IF NOT EXISTS docente (
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    nombres       VARCHAR(100) NOT NULL,
    apellidos     VARCHAR(100) NOT NULL,
    dni           VARCHAR(8)   NOT NULL,
    correo        VARCHAR(100) NOT NULL,
    especialidad  VARCHAR(100) NOT NULL,
    estado        VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_docente_dni UNIQUE (dni)
) ENGINE = InnoDB;

-- -------------------------------------------------------------
-- 3. curso-service (puerto 8082)
--    id_docente referencia a un docente de docente-service.
--    No hay llave foránea porque está en otra base de datos.
-- -------------------------------------------------------------
USE bd2_curso;

CREATE TABLE IF NOT EXISTS curso (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    nombre      VARCHAR(150)   NOT NULL,
    duracion    INT            NOT NULL,
    precio      DECIMAL(10, 2) NOT NULL,
    id_docente  BIGINT         NOT NULL,
    estado      VARCHAR(20)    NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- -------------------------------------------------------------
-- 4. inscripcion-service (puerto 8083)
--    id_curso referencia a un curso de curso-service.
--    No hay llave foránea porque está en otra base de datos.
-- -------------------------------------------------------------
USE bd2_inscripcion;

CREATE TABLE IF NOT EXISTS inscripcion (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    id_curso   BIGINT       NOT NULL,
    nombres    VARCHAR(100) NOT NULL,
    apellidos  VARCHAR(100) NOT NULL,
    dni        VARCHAR(8)   NOT NULL,
    correo     VARCHAR(100) NOT NULL,
    fecha      DATE         NOT NULL,
    estado     VARCHAR(20)  NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

-- -------------------------------------------------------------
-- 5. Verificación
-- -------------------------------------------------------------
SHOW DATABASES LIKE 'bd2_%';
