package Dados;
import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor extends Usuario implements Serializable{
	
	protected ArrayList<String> produtos;
	protected double saldo;
	private ArrayList<ProdutoPedido> produtosVendidos = new ArrayList<ProdutoPedido>();

	public Vendedor(String nome, String senha, String telefone) {
		
		super(nome, senha, telefone);
		this.produtos = new ArrayList<String>();
		
	}
	

	public void addProduto(String nome) {
		this.produtos.add(nome);
		
	}

	public void removeProduto(int i) {
		this.produtos.remove(i);
	}

	public String getNome(int end) {
		
		return produtos.get(end);
	}

	public int getQtdProdutos() {
		// TODO Auto-generated method stub
		return this.produtos.size();
	}


	public void addItemVendido(String nome, double preco, int quantidade, String eMail) {
		// TODO Auto-generated method stub
		this.saldo += preco * quantidade;
		this.produtosVendidos.add(new ProdutoPedido(nome, quantidade, preco, eMail));
	}


	public String minhasVendas() {

		String txt = "";
		for (ProdutoPedido c : produtosVendidos) {
			txt += String.format("Cliente: %s  Produto: %s  preço: R$ %.2f  Quantidade: %d  Total: R$ %.2f\n", 
								c.getVendedor(), c.getNome(), c.getPreco(), c.getQuantidade(), c.getPreco() * c.getQuantidade());
			
		}
		return txt + String.format("\nSaldo: R$ %.2f\n", this.saldo);
	}


	public String getProduto(int menu) {
		// TODO Auto-generated method stub
		return this.produtos.get(--menu);
	}


	public void editarNomeProduto(String nomeAntigo, String novoNome) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.produtos.size(); i++) {
			if(this.produtos.get(i).equals(nomeAntigo)) this.produtos.set(i, novoNome);
	}

	}
	

	

	

}
