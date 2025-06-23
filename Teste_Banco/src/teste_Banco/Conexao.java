package teste_Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao  {
	
	public Connection criarConexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Vai criar a conexão com ("nome do banco", "nome do usuário", "senha")
		Connection conexao=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_teste", "root", "senha");
		System.out.println("Conexão criada");
		return conexao;
	}
}