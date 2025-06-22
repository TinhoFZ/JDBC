package teste_Banco;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		// Vai retornar um objeto, que vai ser armazenado em 'conn'
		Connection conn = conexao.criarConexao();
		
		// A classe vai ter o objeto armazenado em 'conn'
		Usuarios usuario = new Usuarios(conn);
		usuario.criarUsuario();
	}
}
