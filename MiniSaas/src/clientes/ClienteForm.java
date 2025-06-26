package clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ClienteForm {

	public Scanner input = new Scanner(System.in);
	
	private Connection conexao;
	public ClienteForm(Connection conexao) {
		this.conexao = conexao;
	}
	
    public String informarNome() {
    	System.out.println("Qual o nome do cliente?");
    	return input.nextLine();
    }
    
    public String informarCpf() {
    	System.out.println("Qual o cpf do cliente?");
    	return input.nextLine();
    }
    
    public String informarTelefone() {
    	System.out.println("Qual o telefone do cliente?");
    	return input.nextLine();
    }
    
    public String informarEmail() {
    	System.out.println("Qual o email do cliente?");
    	return input.nextLine();
    }

    public String informarEndereco() {
    	System.out.println("Qual o endereço do cliente?");
    	return input.nextLine();
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
    
    public void criarCliente() throws SQLException {
    	String nome = informarNome();
    	String cpf = informarCpf();
    	String telefone = informarTelefone();
    	String email = informarEmail();
    	String endereco = informarEndereco();
    	
    	String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setString(1, nome);
    	stmt.setString(2, cpf);
    	stmt.setString(3, telefone);
    	stmt.setString(4, email);
    	stmt.setString(5, endereco);
    	stmt.executeUpdate();
    	System.out.println("Cliente adicionado com sucesso!");
    }
    
    public void apagarCliente() throws SQLException {
    	int id = informarId();
    	
    	String sql = "DELETE FROM clientes WHERE id_cliente = ?";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setInt(1, id);
    	stmt.executeUpdate();
    }
}
