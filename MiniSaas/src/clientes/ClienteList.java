package clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import home.Home;

public class ClienteList {

	public Scanner input = new Scanner(System.in);
	private Home home = new Home();
	
	private Connection conexao;
	private ClienteForm clienteF;
	public ClienteList(Connection conexao) {
		this.conexao = conexao;
		this.clienteF = new ClienteForm(conexao);
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
    			+ "\n [1] Cadastrar novo cliente"
    			+ "\n [2] Visualizar todos clientes"
    			+ "\n [3] Apagar cliente"
    			+ "\n [4] Voltar");
		
		escolherAcao();
	}
	
    public void escolherAcao() throws SQLException, ClassNotFoundException {

    	int escolha = escolha();
    	
    	switch (escolha) {
    	case 1:
    		clienteF.criarCliente();
    		listarOpcoes();
    		break;
    	case 2:
    		listarcliente();
    		listarOpcoes();
    		break;
    	case 3:
    		clienteF.apagarCliente();
    		listarOpcoes();
    	case 4:
    		home.listarOpcoes();
    		default:
				System.out.println("Escolha uma das opções");

    	}
    }
	public void listarcliente() throws SQLException {
		System.out.println("clientes:");
		String sql = "SELECT * FROM clientes";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet resultado = stmt.executeQuery();
		
		while (resultado.next()) {
			
			int id = resultado.getInt("id_cliente");
			String nome = resultado.getString("nome");
			String cpf = resultado.getString("cpf");
			String telefone = resultado.getString("telefone");
			String email = resultado.getString("email");
			String endereco = resultado.getString("endereco");
			
			System.out.println("ID: " + id + " | Nome: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone + ""
					+ " | Email: " + email + " | Endereço: " + endereco);		
		}
	}
}
