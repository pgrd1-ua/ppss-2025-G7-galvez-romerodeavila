DROP DATABASE IF EXISTS DBUNIT;
CREATE DATABASE IF NOT EXISTS DBUNIT;
USE DBUNIT;

DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
  nif varchar(10) NOT NULL,
  nombre varchar(64) NOT NULL,
  direccion varchar(64),
  email varchar(64),
  fechaNac date,
  PRIMARY KEY (nif)
)
