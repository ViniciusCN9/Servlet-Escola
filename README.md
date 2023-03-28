# Sistema para cadastro de alunos

## Instruções

- Utilize o banco de dados MySql na versão 8.0.32 (Driver disponível na pasta dependencias, para outra versão do MySql instale o driver compatível)
<https://mvnrepository.com/artifact/mysql/mysql-connector-java>

- Execute os comandos SQL presentes no arquivo escola.sql

- Utilize o servidor apache-tomcat 9.0 e configure o Targeted Runtime no Build Path para esse servidor

- Adicione as dependências JAR da pasta deps na pasta webapp/WEB-INF/lib

- Utilize o Insomnia ou o Postman para testes das rotas 
<https://insomnia.rest/download>

## Rotas

Método | Rota | Descrição
-------|------|----------
GET | /Servlet-Escola/alunos | Busca alunos
GET | /Servlet-Escola/alunos/{matricula} | Busca aluno por matricula
POST | /Servlet-Escola/alunos/{nome} | Adiciona aluno
PUT | /Servlet-Escola/alunos/{matricula}/{nome} | Atualiza aluno
DELETE | /Servlet-Escola/alunos/{matricula} | Remove aluno

