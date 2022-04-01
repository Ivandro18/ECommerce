import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Dados.Categoria;
import Dados.Cliente;
import Dados.Produto;
import Dados.ProdutoPedido;
import Dados.Vendedor;

public class ECommerce implements Serializable{
	
	private String nome;
	private ArrayList<Categoria> categorias;
	private Map<String,Cliente> clientes;
	private Map<String,Vendedor> vendedores;
	private Map<String,Produto> produtos;
	private static String nomeProduto;
	private static String eMail;
	
	public ECommerce(String nome) {
		
		this.nome = nome;
		this.categorias = new ArrayList<Categoria>();
		this.clientes = new HashMap<String,Cliente>();
		this.vendedores = new HashMap<String,Vendedor>();
		this.produtos = new HashMap<String,Produto>();
		
		
	}
	
	public void adicionaCategoria(String nome) {
		
		this.categorias.add(new Categoria(nome));
	}
	public String listarCategorias() {
		
		String lista = "Categorias\n";
		if (this.categorias.size() > 0)
		for (int i = 0; i < this.categorias.size(); i++) {
			lista += "  " + (i+1) + ". " + this.categorias.get(i).getNome() + "\n";		
		}
		return lista;
	}
	public void adicionaProduto (int categoria, String nome, double precoProduto, int qtdProduto, String descriProduto) {
		
		this.vendedores.get(ECommerce.eMail).addProduto(nome);
		this.categorias.get(--categoria).addProduto(nome);
		this.produtos.put(nome, new Produto (nome,  precoProduto, qtdProduto, descriProduto,ECommerce.eMail, categoria));
	}
	
	public void editaProduto(int categoria, String nomeProduto2, double precoProduto, int qtdProduto, String descriProduto,
			String eMail) {
		// TODO Auto-generated method stub
		this.vendedores.get(eMail).addProduto(nome);
		this.categorias.get(--categoria).addProduto(nome);
	}	
	
	public void editarprecoProduto(double novoPreco) {
		// TODO Auto-generated method stub
		this.produtos.get(ECommerce.nomeProduto).setPreco(novoPreco);
	}
	
	public void editarQtdProduto(int novoQtd) {
		// TODO Auto-generated method stub
		this.produtos.get(ECommerce.nomeProduto).setQtd(novoQtd);
	}
	
	public void editarDescriProduto(String novaDescricao) {
		// TODO Auto-generated method stub
		this.produtos.get(ECommerce.nomeProduto).setDescricao(novaDescricao);
	}
	
	public void editarNomeProduto(String novoNome) {
		// TODO Auto-generated method stub
		int categoria = this.produtos.get(nomeProduto).getCategoria();
		this.categorias.get(categoria).editaNomeProduto(ECommerce.nomeProduto, novoNome);
		
		this.produtos.put(novoNome, this.produtos.get(ECommerce.nomeProduto));
		this.produtos.remove(ECommerce.nomeProduto);
		
		this.vendedores.get(ECommerce.eMail).editarNomeProduto(ECommerce.nomeProduto,novoNome);		
	}

	public void removeCategoria(int enderecoCategoria) {
		
		this.categorias.remove(--enderecoCategoria);
		
	}

	public void removeProduto(int end) {
		
		String nome = vendedores.get(ECommerce.eMail).getNome(--end);
		//String categoria = produtos.get(nome).getCategoria();
		vendedores.get(ECommerce.eMail).removeProduto(end);		
		this.produtos.remove(nome);
		for (int i = 0; i < this.categorias.size(); i++) {
			if(this.categorias.get(i).getNome().equals(nome)) {
				this.categorias.remove(i);
				break;
			}
			
		}
		
	}

	

	public boolean verificaEmail(String eMail) {
		
		if (clientes.containsKey(eMail) || vendedores.containsKey(eMail)){
			return true;
		}
		
		return false;
	}

	public void adicionaCliente( String eMail,String nome, String senha, String telefone) {
		
		this.clientes.put(eMail,new Cliente(nome, senha, telefone));
			}
	
	public void adicionaVendedor( String eMail,String nome, String senha, String telefone) {
		
		this.vendedores.put(eMail,new Vendedor(nome, senha, telefone));
		}

	public int login(String eMail, String senha) {
		
		if (this.clientes.containsKey(eMail))
			if (this.clientes.get(eMail).getSenha().equals(senha))
				return 1;
		
		if (this.vendedores.containsKey(eMail))
			if (this.vendedores.get(eMail).getSenha().equals(senha))
				return 2;
		
		return 3;
	}

	public String listarUsuario() {
		
		String l = "";
		for (Cliente i : clientes.values()) {
			l = l + i.toString() + "\n";
		}
		for (Vendedor i : vendedores.values()) {
			l = l + i.toString() + "\n";
		}
		return l;
	}

	public String  listarProdutosCategoria(int end) {
		
		return categorias.get(--end).listarProdutos();
	}

	public String exibeProduto(int nunCat, int nunProd) {
		ECommerce.nomeProduto = categorias.get(--nunCat).getProduto(--nunProd);
		return this.produtos.get(nomeProduto).exibeProduto();
	}

	public String meusAnuncios() {
		String txt = "Meus Anúncios\n\n";
		
		for (int i = 0; i < this.vendedores.get(ECommerce.eMail).getQtdProdutos(); i++) {
			String nomeProduto = this.vendedores.get(ECommerce.eMail).getNome(i);
			txt += String.format(" %d. Nome: %s  Preço: R$%.2f   Quantidade: %d  Descrição: %s\n", (i + 1), nomeProduto, this.produtos.get(nomeProduto).getPreco(),
									this.produtos.get(nomeProduto).getQuantidade(), this.produtos.get(nomeProduto).getDescrição());		
		}
		
		return txt + "\n";
	}
	
	public String meuAnuncio(int menu) {
		// TODO Auto-generated method stub
		
		ECommerce.nomeProduto = vendedores.get(ECommerce.eMail).getProduto(menu);
		
		return String.format(" Nome: %s  Preço: R$%.2f   Quantidade: %d  Descrição: %s\n", nomeProduto, this.produtos.get(nomeProduto).getPreco(),
				this.produtos.get(nomeProduto).getQuantidade(), this.produtos.get(nomeProduto).getDescrição());	
	}

	public String addNoCarrinho(int nunCat, int nunProd, int qtdProduto) {
		// TODO Auto-generated method stub
		String nomeProduto = categorias.get(--nunCat).getProduto(--nunProd);
		double preco = produtos.get(nomeProduto).getPreco();
		boolean carrinhoVazio = clientes.get(ECommerce.eMail).getStatusCarrinho();
		String vendedor = produtos.get(nomeProduto).getVendedor();
		this.produtos.get(nomeProduto).reservaQuantidade(qtdProduto);
		
		if (carrinhoVazio) {
			this.clientes.get(ECommerce.eMail).addCarrinho(nomeProduto, qtdProduto, preco, vendedor);
		}else {
			this.clientes.get(ECommerce.eMail).addProdutoCarrinho(nomeProduto, qtdProduto, preco, vendedor);
		}
		
		return "Não vazio";
	}

	public String getCarrinho() {
		// TODO Auto-generated method stub
		return clientes.get(ECommerce.eMail).getCarrinho();
	}

	public void confirmaCompra() {
		
		//this.vendedores.get(vendedor).addVenda()
		ArrayList<ProdutoPedido> pedido = this.clientes.get(ECommerce.eMail).confirmaCompra();
		
		for (ProdutoPedido i : pedido) {
			String vendedor = i.getVendedor();
			this.vendedores.get(vendedor).addItemVendido(i.getNome(), i.getPreco(), i.getQuantidade(), ECommerce.eMail);
		}
		this.clientes.get(ECommerce.eMail).addCarrinho();
	}

	public int getqtdEstoque(int nunCat, int nunProd) {
		
		return produtos.get(nomeProduto).getQuantidade();
	}

	public String getMinhasVendas(String eMail) {
		
		return this.vendedores.get(eMail).minhasVendas();
	}

	public String getMeusPedidos() {
		
		return this.clientes.get(ECommerce.eMail).getMeusPedidos();
	}

	public void editaCategoria(int menu, String novoNome) {
		// TODO Auto-generated method stub
		this.categorias.get(--menu).setNome(novoNome);
	}



	public void setUsuario(String eMail) {
		// TODO Auto-generated method stub
		ECommerce.eMail = eMail;
	}

	

	

	


}
