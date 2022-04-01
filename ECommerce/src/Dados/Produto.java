package Dados;
import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable{

	protected String nome;
	protected int categoria;
	protected int quantidade;
	protected double preco;
	protected String descricao;
	protected String vendedor;
	
	public Produto(String nome, double precoProduto, int quantidade, String descricao, String vendedor, int categoria) {
		
		
		this.nome = nome;
		this.preco = precoProduto;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.vendedor = vendedor;
		this.categoria = categoria;
	}
	public Produto(String nome, double precoProduto, int quantidade, String vendedor) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.preco = precoProduto;
		this.quantidade = quantidade;
		this.vendedor = vendedor;
	}
	public String getNome () {
		
		return this.nome;
	}
	public String exibeProduto() {
		
		return  String.format("\n\tNome:       %s"
							+ "\n\tPreço:      R$ %.2f"
							+ "\n\tQuantidade: %d"
							+ "\n\tDescriçao:  %s", this.nome,this.preco, this.quantidade, this.descricao);
	}
	public int getCategoria() {
		// TODO Auto-generated method stub
		return this.categoria;
	}
	public double getPreco() {
		// TODO Auto-generated method stub
		return this.preco;
	}
	public String getVendedor() {
		// TODO Auto-generated method stub
		return this.vendedor;
	}
	
	public int getQuantidade() {
		
		return this.quantidade;
	}
	
	public void reservaQuantidade(int qtdProduto) {

		this.quantidade -= qtdProduto;
		
	}
	public String getDescrição() {
		// TODO Auto-generated method stub
		return this.descricao;
	}
	public void setPreco(double novoPreco) {
		// TODO Auto-generated method stub
		this.preco = novoPreco;
	}
	public void setQtd(int novoQtd) {
		// TODO Auto-generated method stub
		this.quantidade = novoQtd;
	}
	public void setDescricao(String novaDescricao) {
		// TODO Auto-generated method stub
		this.descricao = novaDescricao;
	}
}
