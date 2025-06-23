package home;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.Conexao;
import usuarios.UsuarioList;

public class Home {

	public static Scanner input = new Scanner(System.in);
	private Connection conn;
	
	public void conectarBanco() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		// Vai retornar um objeto, que vai ser armazenado em 'conn'
		conn = conexao.criarConexao();
		
		listarOpcoes();
	}
	
	public void listarOpcoes() throws SQLException, ClassNotFoundException {
		System.out.println("O que deseja fazer?"
				+ "\n [1] Ir para usuários"
				+ "\n [2] Sair");
		escolherOpcoes();
	}
	
	// Método para retornar a escolha do usuário
	public int escolha() {
		int escolha = 0;
		try {
			escolha = input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Você inseriu um valor inválido");
		}
		return escolha;
	}
	
	public void escolherOpcoes() throws SQLException, ClassNotFoundException {
		
		int escolha = escolha();
		
		switch(escolha) {
		case 1:
			UsuarioList usuario = new UsuarioList(conn);
			usuario.listarOpcoes();
			break;
		case 2:
			System.exit(0);
			break;
			default:
				System.out.println("Escolha uma das opções");
		}
	}
}
