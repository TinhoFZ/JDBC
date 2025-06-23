package teste_Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException; /* Exceção caso o tipo de data esteja incorreto */

public class Usuarios {

	public static Scanner input = new Scanner(System.in);
	
	// Dá acesso ao banco de dados
	private Connection conexao;
    public Usuarios(Connection conexao) {
        this.conexao = conexao;
    }
    
    public String informarNome() {
    	System.out.println("Qual o seu nome?");
    	return input.nextLine();
    }
    
    public String informarEmail() {
    	System.out.println("Qual o seu email?");
    	return input.nextLine();
    }
    
    public String informarSenha() {
    	System.out.println("Qual a sua senha?");
    	return input.nextLine();
    }
    
    public int informarTipo() {
    	int escolha = 0;
    	try {
	    	System.out.println("Você é adm? [0] não | [1] sim");
	    	escolha = input.nextInt();
	    	if (escolha > 1 || escolha < 0) {
	    		System.out.println("Escolha uma das opções");
	    	}
    	} catch (InputMismatchException e) {
    		System.out.println("Você inseriu um valor inválido");
    	}
    	return escolha;
    }
    
	public void criarUsuario() throws SQLException {
		
		String nome = informarNome();
		String email = informarEmail();
		String senha = informarSenha();
		int adm = informarTipo();
		
		// É um comando em sql, os valores estão como '?' porque serão preenchidos depois
		// '?' é reconhecido como um parâmetro
		String sql = "INSERT INTO usuarios (nome, email, senha, adm) VALUES (?, ?, ?, ?)";
		// Está preparando o banco para receber um comando
		PreparedStatement stmt = conexao.prepareStatement(sql);
		// Vai preencher o valor '1' com o nome 'joão', vai preecnher o valor '2' com a idade '17'
		stmt.setString(1, nome);
		stmt.setString(2, email);
		stmt.setString(3, senha);
		stmt.setInt(4, adm);
		stmt.executeUpdate();
		System.out.println("Usuário adicionado com sucesso!");
		listarUsuario();
		
	}
	
	public void listarUsuario() throws SQLException {
		System.out.println("Usuários:");
		String sql = "SELECT * FROM usuarios";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		// Result set vai armazenar os resultados na variável
		ResultSet resultado = stmt.executeQuery();
		// Enquanto ainda existir uma linha o código vai rodar
		while (resultado.next()) {
			// Vai armazenar o valor de uma coluna em uma variável
			int id = resultado.getInt("id");
			String nome = resultado.getString("nome");
			String email = resultado.getString("email");
			String senha = resultado.getString("senha");
			int adm = resultado.getInt("adm");
			String tipo = "";
			if (adm == 0) {
				tipo = "comum";
			} else {
				tipo = "adm";
			}
			
			// Vai mostrar todos os usuários apartir dessa variável
			System.out.println("ID: " + id + "| Nome: " + nome + "| Email: " + email + "| Senha: " + senha + "| Tipo: " + tipo);
		}
	}
}
