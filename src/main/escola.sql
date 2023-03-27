CREATE DATABASE IF NOT EXISTS escola;

CREATE TABLE escola.alunos (
  matricula CHAR(32) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  PRIMARY KEY (matricula));
