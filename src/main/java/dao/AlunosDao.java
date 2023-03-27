package dao;

import java.sql.*;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import connections.IDbConnection;
import connections.MySqlDbConnection;
//import factory.Inject;
import models.Aluno;

public class AlunosDao implements IAlunosDao {
	
	//@Inject //Implementação futura
	private IDbConnection dbConnection;
	
	
	
	public AlunosDao() throws SQLException, ClassNotFoundException {
		this.dbConnection = new MySqlDbConnection();
	}

	public List<Aluno> GetAlunos() throws SQLException {
		try {
			String sql = "SELECT * FROM alunos";
			ResultSet resultSet = this.dbConnection.query(sql);
			
			List<Aluno> alunos = new ArrayList<Aluno>();
			while (resultSet.next()) {
				Aluno aluno = new Aluno(resultSet.getString("matricula"), resultSet.getString("nome")); 
                alunos.add(aluno);
            }
			
			return alunos;
			
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public Aluno GetAluno(String matricula) throws SQLException {
		try {
			String sql = "SELECT * FROM alunos WHERE matricula = ?;";
			ResultSet resultSet = this.dbConnection.query(sql, matricula);
			
			Aluno aluno = new Aluno();
			while (resultSet.next()) {
				aluno.setMatricula(resultSet.getString("matricula"));
				aluno.setNome(resultSet.getString("nome"));
			}
			
			return aluno;
			
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public void InsertAluno(String nome) throws SQLException {
		try {
			String sql = "INSERT INTO alunos (matricula, nome) values (?, ?);";
			
			String[] parameters = new String[2];
			parameters[0] = UUID.randomUUID().toString();
			parameters[1] = nome;
			
			this.dbConnection.execute(sql, parameters);
			
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public void UpdateAluno(String matricula, String nome) throws SQLException {
		try {
			String sql = "UPDATE alunos SET nome = ? WHERE matricula = ?;";
			
			String[] parameters = new String[2];
			parameters[0] = nome;
			parameters[1] = matricula;
			
			this.dbConnection.execute(sql, parameters);
			
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public void DeleteAluno(String matricula) throws SQLException {
		try {
			String sql = "DELETE FROM alunos WHERE matricula = ?;";
			
			this.dbConnection.execute(sql, matricula);
			
		} catch(SQLException e) {
			throw e;
		}
	}
}
