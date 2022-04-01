package Dados;
import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Usuario implements Serializable{
	
	protected ArrayList<Pedido> pedidos;
	private boolean CarrinhoVazio= true;

	public Cliente(String nome, String senha, String telefone) {
		
		super(nome, senha, telefone);
		this.pedidos = new ArrayList<Pedido>();
		
	}

	public void addCarrinho() {
		// TODO Auto-generated method stub
		this.pedidos.add(new Pedido());
	}
	public void addCarrinho(String nomeProduto, int qtdProduto, double preco, String vendedor) {

		this.pedidos.add(new Pedido(nomeProduto, qtdProduto, preco, vendedor));
		this.CarrinhoVazio = false;
	}

	public void addProdutoCarrinho(String nome, int qtdProduto, double preco, String vendedor) {
		
		this.pedidos.get(pedidos.size() - 1).addProduto(nome, qtdProduto, preco, vendedor);
	}

	public String getCarrinho() {
		
		return this.pedidos.get(pedidos.size() - 1).getProdutos();
	}

	public boolean getStatusCarrinho() {
		
		return this.CarrinhoVazio;
	}

	public ArrayList<ProdutoPedido> confirmaCompra() {
		
		this.CarrinhoVazio = true;
		
		return this.pedidos.get(this.pedidos.size() - 1).getListaProdutos();	
	}

	public String getMeusPedidos() {
		
		String txt = "";
		for (int i = 0; i < this.pedidos.size() - 1; i++) {
			txt += String.format("\n\nPedido: %d\n\n%s", i + 1, this.pedidos.get(i).getProdutos());
		}
		return txt;
	}



}
