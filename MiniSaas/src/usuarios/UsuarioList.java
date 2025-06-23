package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import home.Home;

public class UsuarioList {

	public static Scanner input = new Scanner(System.in);
	private Home home = new Home();
	
	private Connection conexao;
	private UsuarioForm usuarioF;
    public UsuarioList(Connection conexao) {
        this.conexao = conexao;
        this.usuarioF = new UsuarioForm(conexao);
    }
    
	public int escolha() {
		int escolha = 0;
		try {
			escolha = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Você inseriu um valor inválido");
		}
		return escolha;
	}
	
	public void listarOpcoes() throws SQLException, ClassNotFoundException {
    	System.out.println("O que deseja fazer? "
    			+ "\n [1] Cadastrar novo usuário"
    			+ "\n [2] Visualizar todos usuários"
    			+ "\n [3] Voltar");
		
		escolherAcao();
	}
	
    public void escolherAcao() throws SQLException, ClassNotFoundException {

    	int escolha = escolha();
    	
    	switch (escolha) {
    	case 1:
    		usuarioF.criarUsuario();
    		listarOpcoes();
    		break;
    	case 2:
    		listarUsuario();
    		listarOpcoes();
    		break;
    	case 3:
    		home.conectarBanco();
    		break;
    		default:
				System.out.println("Escolha uma das opções");

    	}
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
			System.out.println("ID: " + id + " | Nome: " + nome + " | Email: " + email + " | Senha: " + senha + " | Tipo: " + tipo);
		}
	}
}
