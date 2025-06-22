package teste_Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuarios {

	// Dá acesso ao banco de dados
	private Connection conexao;
    public Usuarios(Connection conexao) {
        this.conexao = conexao;
    }
    
	public void criarUsuario() throws SQLException {
		// É um comando em sql, os valores estão como '?' porque serão preenchidos depois
		// '?' é reconhecido como um parâmetro
		String sql = "INSERT INTO usuarios (nome, idade) VALUES (?, ?)";
		// Está preparando o banco para receber um comando
		PreparedStatement stmt = conexao.prepareStatement(sql);
		// Vai preencher o valor '1' com o nome 'joão', vai preecnher o valor '2' com a idade '17'
		stmt.setString(1, "João");
		stmt.setString(2, "17");
		stmt.executeUpdate();
		System.out.println("Usuário adicionar com sucesso!");
	}
}
