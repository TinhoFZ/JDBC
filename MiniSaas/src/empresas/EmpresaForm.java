package empresas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class EmpresaForm {

	public Scanner input = new Scanner(System.in);
	
	private Connection conexao;
	public EmpresaForm(Connection conexao) {
		this.conexao = conexao;
	}
	
    public String informarNome() {
    	System.out.println("Qual o nome da empresa?");
    	return input.nextLine();
    }
    
    public String informarCnpj() {
    	System.out.println("Qual o cnpj da empresa?");
    	return input.nextLine();
    }
    
    public String informarRazao() {
    	System.out.println("Qual a razão social da emrpesa?");
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
    
    public void criarEmpresa() throws SQLException {
    	String nome = informarNome();
    	String cnpj = informarCnpj();
    	String razao = informarRazao();
    	
    	String sql = "INSERT INTO empresas (nome, cnpj, razao_social) VALUES (?, ?, ?)";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setString(1, nome);
    	stmt.setString(2, cnpj);
    	stmt.setString(3, razao);
    	stmt.executeUpdate();
    	System.out.println("Empresa adicionada com sucesso!");
    }
}
