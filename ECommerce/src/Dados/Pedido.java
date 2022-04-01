package Dados;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable{
	private ArrayList<ProdutoPedido> produtos = new ArrayList<ProdutoPedido>();
	
	private double totalPedido;

	public Pedido(String nomeProduto, int qtdProduto, double preco, String vendedor) {
		// TODO Auto-generated constructor stub
		this.produtos.add(new ProdutoPedido(nomeProduto, qtdProduto, preco, vendedor));
	}
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public void addProduto(String nomeProduto, int qtdProduto, double preco, String vendedor) {
		this.produtos.add(new ProdutoPedido(nomeProduto, qtdProduto, preco, vendedor));
	}

	public String getProdutos() {
//		String txt = "      NOME\t\tQUANTIDADE\t\tPREÇO\t\tTOTAL\t\t\n\n";
		String txt = "";
		for	(int i = 0; i < this.produtos.size(); i++) {
			txt += (i + 1) + ". " + this.produtos.get(i).getProduto() + "\n";
 		}
		
		totalPedido = 0;
		for (ProdutoPedido preco : produtos) {
			totalPedido += preco.getPreco() * preco.getQuantidade();
		}
		
		txt += String.format("\nTotal: %.2f", totalPedido);
		
		return txt;
	}

	public ArrayList<ProdutoPedido> getListaProdutos() {
		// TODO Auto-generated method stub
		return this.produtos;
	}

	
}
