package produtos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ProdutoForm {

	public Scanner input = new Scanner(System.in);
	
	private Connection conexao;
	public ProdutoForm(Connection conexao) {
		this.conexao = conexao;
	}
	
    public String informarNome() {
    	System.out.println("Qual o nome do produto?");
    	return input.nextLine();
    }
    
    public String informarDescricao() {
    	System.out.println("Qual a descrição do produto?");
    	return input.nextLine();
    }
    
    public int informarPrecoVenda() {
    	int escolha = 0;
    	try {
	    	System.out.println("Qual o preço de venda?");
	    	escolha = input.nextInt();
	    	input.nextLine();
	    } catch (InputMismatchException e) {
	    	System.out.println("Você inseriu um valor inválido");
	    }
    	return escolha;
    }
    
    public double informarPrecoCompra() {
    	double escolha = 0;
    	try {
	    	System.out.println("Qual o preço de compra?");
	    	escolha = input.nextDouble();
	    	input.nextDouble();
	    } catch (InputMismatchException e) {
	    	System.out.println("Você inseriu um valor inválido");
	    }
    	return escolha;
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
    
    public int informarCategoria() {
    	int escolha = 0;
    	try {
	    	System.out.println("Qual a id da categoria?");
	    	escolha = input.nextInt();
	    	input.nextLine();
	    } catch (InputMismatchException e) {
	    	System.out.println("Você inseriu um valor inválido");
	    }
    	return escolha;
    }
    
    public int informarQuantidade() {
    	int escolha = 0;
    	try {
	    	System.out.println("Qual a quantidade?");
	    	escolha = input.nextInt();
	    	input.nextLine();
	    } catch (InputMismatchException e) {
	    	System.out.println("Você inseriu um valor inválido");
	    }
    	return escolha;
    }
    
    
    public void criarProduto() throws SQLException {
    	String nome = informarNome();
    	String descricao = informarDescricao();
    	double venda = informarPrecoVenda();
    	double compra = informarPrecoCompra();
    	int id = informarId();
    	int quantidade = informarQuantidade();
    	
    	String sql = "INSERT INTO produtos (nome, descricao, preco_venda, preco_compra, id_categoria, quantidadeEstoque) VALUES (?, ?, ?, ?, ?, ?)";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setString(1, nome);
    	stmt.setString(2, descricao);
    	stmt.setDouble(3, venda);
    	stmt.setDouble(4, compra);
    	stmt.setInt(5, id);
    	stmt.setInt(6, quantidade);
    	stmt.executeUpdate();
    	System.out.println("Cliente adicionado com sucesso!");
    }
    
    public void criarCategoria() throws SQLException {
    	String nome = informarNome();
    	
    	String sql = "INSERT INTO categorias_produtos (nome) VALUES (?)";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setString(1, nome);
    	stmt.executeUpdate();
    	System.out.println("Categoria adicionada com sucesso!");
    }
    
    public void apagarProduto() throws SQLException {
    	int id = informarId();
    	
    	String sql = "DELETE FROM produtos WHERE id_produto = ?";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setInt(1, id);
    	stmt.executeUpdate();
    }
    
    public void apagarCategora() throws SQLException {
    	int id = informarId();
    	
    	String sql = "DELETE FROM categoria_produtos WHERE id_categoria - (?)";
    	PreparedStatement stmt = conexao.prepareStatement(sql);
    	
    	stmt.setInt(1, id);
    	stmt.executeUpdate();
    }
}
