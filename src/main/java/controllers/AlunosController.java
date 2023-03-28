package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import dao.IAlunosDao;
import dao.AlunosDao;
//import factory.Inject;
import models.Aluno;

@WebServlet(urlPatterns = {"/alunos", "/alunos/*"})
public class AlunosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//@Inject // Implementação futura
	private IAlunosDao alunosDao;
	private Gson gson;

    public AlunosController() throws SQLException, ClassNotFoundException {
    	super();
    	this.alunosDao = new AlunosDao();
    	this.gson = new Gson();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String message = "";
		
		try {
			String matricula = "";
			Pattern routePattern = Pattern.compile("^/Servlet-Escola/alunos/([\\w-]+)$");
			Matcher matcher = routePattern.matcher(request.getRequestURI());
			if (matcher.matches()) {
				matricula = matcher.group(1);
			}
			
			
			if (matricula != "") {
				Aluno aluno = alunosDao.GetAluno(matricula);
				response.setStatus(HttpServletResponse.SC_OK);
				message = gson.toJson(aluno);
				
			} else {
				List<Aluno> alunos = alunosDao.GetAlunos();
				response.setStatus(HttpServletResponse.SC_OK);
				message = gson.toJson(alunos);
			}
			
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			message = gson.toJson("Ocorreu o erro: " + e.getMessage()) ;
		}
		
		out.print(message);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String message = "";
		
		try {
			String nome = "";
			Pattern routePattern = Pattern.compile("^/Servlet-Escola/alunos/([\\w-]+)$");
			Matcher matcher = routePattern.matcher(request.getRequestURI());
			if (matcher.matches()) {
				nome = matcher.group(1);
			}
			
			if (nome != "") {
				alunosDao.InsertAluno(nome);
				response.setStatus(HttpServletResponse.SC_OK);
				message = gson.toJson("Aluno inserido com sucesso!");
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				message = gson.toJson("Nome ausente!");
			}
			
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			message = gson.toJson("Ocorreu o erro: " + e.getMessage()) ;
		}
		
		out.print(message);
		out.flush();
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String message = "";
		
		try {
			String matricula = "";
			String nome = "";
			Pattern routePattern = Pattern.compile("^/Servlet-Escola/alunos/([\\w-]+)/([\\w-]+)$");
			Matcher matcher = routePattern.matcher(request.getRequestURI());
			if (matcher.matches()) {
				matricula = matcher.group(1);
				nome = matcher.group(2);
			}
			
			if (matricula != "" || nome != "") {
				alunosDao.UpdateAluno(matricula, nome);
				response.setStatus(HttpServletResponse.SC_OK);
				message = gson.toJson("Aluno atualizado com sucesso!");
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				message = gson.toJson("Matricula ou nome ausentes!");
			}
			
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			message = gson.toJson("Ocorreu o erro: " + e.getMessage()) ;
		}
		
		out.print(message);
		out.flush();
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String message = "";
		
		try {
			String matricula = "";
			Pattern routePattern = Pattern.compile("^/Servlet-Escola/alunos/([\\w-]+)$");
			Matcher matcher = routePattern.matcher(request.getRequestURI());
			if (matcher.matches()) {
				matricula = matcher.group(1);
			}
			
			if (matricula != "") {
				alunosDao.DeleteAluno(matricula);
				response.setStatus(HttpServletResponse.SC_OK);
				message = gson.toJson("Aluno removido com sucesso!");
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				message = gson.toJson("Matricula ausente!");
			}
			
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			message = gson.toJson("Ocorreu o erro: " + e.getMessage()) ;
		}
		
		out.print(message);
		out.flush();
	}

}
