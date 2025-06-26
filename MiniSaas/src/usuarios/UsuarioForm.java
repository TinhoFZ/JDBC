package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException; /* Exceção caso o tipo de data esteja incorreto */

public class UsuarioForm {

	public static Scanner input = new Scanner(System.in);
	
	// Dá acesso ao banco de dados
	private Connection conexao;
    public UsuarioForm(Connection conexao) {
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
	    	System.out.println("Você é adm? [1] não | [2] sim");
	    	escolha = input.nextInt();
	    	input.nextLine();
	    	if (escolha > 1 || escolha < 0) {
	    		System.out.println("Escolha uma das opções");
	    	}
    	} catch (InputMismatchException e) {
    		System.out.println("Você inseriu um valor inválido");
    	}
    	return escolha - 1;
    }

    public int informarId() {
    	int escolha = 0;
    	try {
	    	System.out.println("Qual o id?");
	    	escolha = input.nextInt();
	    	input.nextLine();
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
		// Vai preencher o valor '1' com a variável 'nome', vai preecnher o valor '2' com a variável 'email' etc
		stmt.setString(1, nome);
		stmt.setString(2, email);
		stmt.setString(3, senha);
		stmt.setInt(4, adm);
		// Vai atualizar o banco após as mudanças feitas
		stmt.executeUpdate();
		System.out.println("Usuário adicionado com sucesso!");
		
	}
	
	// Usuário vai escolher o que editar
	public String escolherEdicao() {
		String editar = "";
		System.out.println("O que deseja editar?"
				+ "\n [1] Nome"
				+ "\n [2] Email"
				+ "\n [3] Senha"
				+ "\n [4] Tipo");
		try {
			int escolha = input.nextInt();
			input.nextLine();
			switch (escolha) {
			case 1:
				editar = "nome";
				break;
			case 2:
				editar = "email";
				break;
			case 3:
				editar = "senha";
				break;
			case 4:
				editar = "adm";
				break;
				default:
					System.out.println("Escolha uma das opções");
			}
		} catch (InputMismatchException e) {
			System.out.println("Você inseriu um valor inválido");
		}
		return editar;
	}
	
	public void editarTudoUsuario() throws SQLException {
		String nome = informarNome();
		String email = informarEmail();
		String senha = informarSenha();
		int adm = informarTipo();
		int id = informarId();
		
		String sql = "UPDATE usuarios "
				+ "SET nome = ?, "
				+ "email = ?,"
				+ "senha = ?,"
				+ "adm = ? "
				+ "WHERE id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, nome);
		stmt.setString(2, email);
		stmt.setString(3, senha);
		stmt.setInt(4, adm);
		stmt.setInt(5, id);
		stmt.executeUpdate();
	}
	public void editarParteUsuario() throws SQLException {
		String edicao = escolherEdicao();
		String novaString = "";
		int novaInt = 0;
		// Vai definir se a escolha foi uma String ou int
		boolean mudancaString = false;
		
		// O usuário vai definir com o que ele vai atualizar a tabela
		switch (edicao) {
		case "nome":
			novaString = informarNome();
			mudancaString = true;
			break;
		case "email":
			novaString = informarEmail();
			mudancaString = true;
			break;
		case "senha":
			novaString = informarSenha();
			mudancaString = true;
			break;
		case "adm":
			novaInt = informarTipo();
			mudancaString = false;
			break;
		}
		
		int id = informarId();
		
		// Vai atualizar o usuário na coluna 'edicao' por 'novaString/novaInt' onde o id é igual a 'id'
		String sql = "UPDATE usuarios "
				+ "SET " + edicao + " = ? "
				+ "WHERE id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);

		if (mudancaString == true) {
			stmt.setString(1, novaString);
		} else {
			stmt.setInt(1, novaInt);
		}
		stmt.setInt(2, id);
		stmt.executeUpdate();
	}
	
	public void apagarUsuario() throws SQLException {
		
		int id = informarId();
		
		String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setInt(1, id);
		stmt.executeUpdate();
		
		System.out.println("Usuário apagado com sucesso!");
	}
	
}