package Dados;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable{
	
	private String nome;
	private ArrayList<String> produtos;
	
	public Categoria(String nome) {
		this.nome = nome;
		this.produtos = new ArrayList<String>();
	}	

	public void addProduto(String nome) {
		this.produtos.add(nome);
	}

	
	public String getNome() {
		return this.nome;
	}

	public String listarProdutos() {
		String lista = this.nome + "\n";
		for (int i = 0; i < this.produtos.size(); i++) {
			lista += "  " + (i+1) + ". " + this.produtos.get(i) + "\n";		
		}
		return lista;
	}

	public String getProduto(int i) {
		// TODO Auto-generated method stub
		return produtos.get(i);
	}

	public void setNome(String novoNome) {
		// TODO Auto-generated method stub
		this.nome = novoNome;
	}

	public void editaNomeProduto(String nomeAntigo, String novoNome) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.produtos.size(); i++) {
			if(this.produtos.get(i).equals(nomeAntigo)) this.produtos.set(i, novoNome);
	}
	}

}
