package empresas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import home.Home;

public class EmpresaList {

	public Scanner input = new Scanner(System.in);
	private Home home = new Home();
	
	private Connection conexao;
	private EmpresaForm empresaF;
	public EmpresaList(Connection conexao) {
		this.conexao = conexao;
		this.empresaF = new EmpresaForm(conexao);
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
    			+ "\n [1] Cadastrar nova empresa"
    			+ "\n [2] Visualizar todas empresas"
    			+ "\n [3] Voltar");
		
		escolherAcao();
	}
	
    public void escolherAcao() throws SQLException, ClassNotFoundException {

    	int escolha = escolha();
    	
    	switch (escolha) {
    	case 1:
    		empresaF.criarEmpresa();
    		listarOpcoes();
    		break;
    	case 2:
    		listarEmpresa();
    		listarOpcoes();
    		break;
    	case 3:
    		home.listarOpcoes();
    		default:
				System.out.println("Escolha uma das opções");

    	}
    }
	public void listarEmpresa() throws SQLException {
		System.out.println("Empresas:");
		String sql = "SELECT * FROM empresas";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		ResultSet resultado = stmt.executeQuery();
		
		while (resultado.next()) {
			
			int id = resultado.getInt("id_empresa");
			String nome = resultado.getString("nome");
			String cnpj = resultado.getString("cnpj");
			String razao = resultado.getString("razao_social");
			
			System.out.println("ID: " + id + " | Nome: " + nome + " | CNPJ: " + cnpj + " | Razão social: " + razao);		
		}
	}
}
