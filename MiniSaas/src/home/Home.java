package home;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import main.Conexao;
import usuarios.UsuarioList;
import empresas.EmpresaList;

public class Home {

	public static Scanner input = new Scanner(System.in);
	private Connection conn;
	
	public void conectarBanco() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		// Vai retornar um objeto, que vai ser armazenado em 'conn'
		conn = conexao.criarConexao();
	}
	
	public void listarOpcoes() throws SQLException, ClassNotFoundException {
		System.out.println("O que deseja fazer?"
				+ "\n [1] Ir para usuários"
				+ "\n [2] Ir para empresas"
				+ "\n [3] Sair");
		escolherOpcoes();
	}
	
	// Método para retornar a escolha do usuário
	public int escolha() {
		int escolha = 0;
		// O código vai rodar enquanto escolha não for igual a uma das opções
		try {
			escolha = input.nextInt();
			input.nextLine();
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
			EmpresaList empresa = new EmpresaList(conn);
			empresa.listarOpcoes();
		case 3:
			System.exit(0);
			break;
			default:
				System.out.println("Escolha uma das opções");
		}
	}
}
