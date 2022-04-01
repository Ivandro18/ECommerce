package Dados;

public class ProdutoPedido extends Produto{
	
	

	public ProdutoPedido( String nome, int quantidade, double preco, String vendedor) {
		
		super(nome, preco, quantidade, vendedor);
		
	}

	public String getProduto() {
		double total = this.quantidade * this.preco;
		return String.format("Nome: %s  Quantidade: %d  preço: %.2f  Total: %.2f",
							this.nome, this.quantidade, this.preco, total);
	}

	public int getQuantidade() {
		// TODO Auto-generated method stub
		return this.quantidade;
	}

	

}
