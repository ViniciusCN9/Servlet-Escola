package dao;

import java.sql.SQLException;
import java.util.List;

import models.Aluno;

public interface IAlunosDao {
	public List<Aluno> GetAlunos() throws SQLException;
	public Aluno GetAluno(String matricula) throws SQLException;
	public void InsertAluno(String nome) throws SQLException;
	public void UpdateAluno(String matricula, String nome) throws SQLException;
	public void DeleteAluno(String matricula) throws SQLException;
}
